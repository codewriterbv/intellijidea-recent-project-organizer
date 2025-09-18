package be.codewriter.recent_projects_organizer

import com.intellij.ide.RecentProjectListActionProvider
import com.intellij.ide.ReopenProjectAction
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.ProjectManager
import java.io.File

class OrganizedRecentProjectsSubmenu : DefaultActionGroup("Recent Projects (Organized)", true) {

    override fun update(e: AnActionEvent) {
        println("OrganizedRecentProjectsSubmenu.update() called") 
        removeAll()

        // Get recent projects and organize them
        val recentProjects = getRecentProjects()
        println("Found ${recentProjects.size} recent projects for submenu") 

        val organizedProjects = organizeProjectsByFirstWord(recentProjects)
        println("Organized into ${organizedProjects.size} groups for submenu") 

        // Create subgroups for each first word
        organizedProjects.forEach { (groupName, projects) ->
            println("Group '$groupName' has ${projects.size} projects") 

            if (projects.size == 1) {
                // If only one project, add it directly
                add(createProjectAction(projects.first()))
            } else {
                // Create a subgroup for multiple projects
                val subGroup = DefaultActionGroup(groupName, true)
                projects.forEach { project ->
                    subGroup.add(createProjectAction(project))
                }
                add(subGroup)
            }
        }
    }

    private fun getRecentProjects(): List<ProjectInfo> {
        println("Getting recent projects for submenu...") 

        return RecentProjectListActionProvider
            .getInstance()
            .getActions(addClearListItem = false, useGroups = false)
            .filterIsInstance<ReopenProjectAction>()
            .map { action ->
                val projectPath = action.projectPath
                val projectName = action.projectName ?: File(projectPath).name
                val displayName = action.projectDisplayName ?: projectName
                println("Found project: $projectName at $projectPath")
                ProjectInfo(projectName, displayName, projectPath)
            }
    }

    private fun organizeProjectsByFirstWord(projects: List<ProjectInfo>): Map<String, List<ProjectInfo>> {
        return projects
            .groupBy { getFirstWord(it) }
            .toSortedMap()
    }

    private fun getFirstWord(projectInfo: ProjectInfo): String {
        return getFirstWord(projectInfo.displayName)
    }

    fun getFirstWord(text: String): String {
        val firstWord = text
            .split(Regex("[\\s\\-_.]"))
            .firstOrNull { it.isNotBlank() }
            ?: ""

        println("getFirstWord('$text') -> '$firstWord'")
        return firstWord
    }

    private fun createProjectAction(projectInfo: ProjectInfo): AnAction {
        return object : AnAction(projectInfo.displayName) {
            override fun actionPerformed(e: AnActionEvent) {
                println("Opening project: ${projectInfo.displayName}") // DEBUG LOG
                // Open the project using the proper ProjectManager API instead of calling the original action
                ProjectManager.getInstance().loadAndOpenProject(projectInfo.path)
            }

            override fun update(e: AnActionEvent) {
                e.presentation.description = "Open project: ${projectInfo.path}"
            }
        }
    }
}