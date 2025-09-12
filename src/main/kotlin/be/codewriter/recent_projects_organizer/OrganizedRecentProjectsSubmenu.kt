package be.codewriter.recent_projects_organizer

import com.intellij.ide.RecentProjectListActionProvider
import com.intellij.ide.ReopenProjectAction
import com.intellij.openapi.actionSystem.*
import java.io.File

class OrganizedRecentProjectsSubmenu : DefaultActionGroup("Recent Projects (Organized)", true) {

    override fun update(e: AnActionEvent) {
        println("OrganizedRecentProjectsSubmenu.update() called") // DEBUG LOG
        removeAll()

        // Get recent projects and organize them
        val recentProjects = getRecentProjects()
        println("Found ${recentProjects.size} recent projects for submenu") // DEBUG LOG

        val organizedProjects = organizeProjectsByFirstWord(recentProjects)
        println("Organized into ${organizedProjects.size} groups for submenu") // DEBUG LOG

        // Create subgroups for each first word
        organizedProjects.forEach { (groupName, projects) ->
            println("Group '$groupName' has ${projects.size} projects") // DEBUG LOG

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

        // Add separator and utility actions at the bottom
        if (organizedProjects.isNotEmpty()) {
            addSeparator()
            add(object : AnAction("Manage Projects...") {
                override fun actionPerformed(e: AnActionEvent) {
                    // Delegate to the original manage projects action
                    val actionManager = ActionManager.getInstance()
                    val manageAction = actionManager.getAction("ManageRecentProjects")
                    manageAction?.actionPerformed(e)
                }
            })

            add(object : AnAction("Clear List") {
                override fun actionPerformed(e: AnActionEvent) {
                    // You can implement clear functionality here
                    println("Clear recent projects list")
                }
            })
        }
    }

    private fun getRecentProjects(): List<ProjectInfo> {
        println("Getting recent projects for submenu...") // DEBUG LOG

        return RecentProjectListActionProvider
            .getInstance()
            .getActions(addClearListItem = false, useGroups = false)
            .filterIsInstance<ReopenProjectAction>()
            .map { action ->
                val projectPath = action.projectPath
                val projectName = action.projectName ?: File(projectPath).name
                val displayName = action.projectDisplayName ?: projectName
                println("Found project: $projectName at $projectPath") // DEBUG LOG
                ProjectInfo(projectName, displayName, projectPath, action)
            }
    }

    private fun organizeProjectsByFirstWord(projects: List<ProjectInfo>): Map<String, List<ProjectInfo>> {
        return projects
            .groupBy { getFirstWord(it) }
            .toSortedMap()
    }

    private fun getFirstWord(projectInfo: ProjectInfo): String {
        val firstWord = projectInfo.displayName
            .split(Regex("[\\s\\-_.]"))
            .firstOrNull { it.isNotBlank() }
            ?.lowercase()
            ?: "Other"

        val result = firstWord.replaceFirstChar { it.uppercase() }
        println("getFirstWord('$projectInfo.name') -> '$result'") // DEBUG LOG
        return result
    }

    private fun createProjectAction(projectInfo: ProjectInfo): AnAction {
        return object : AnAction(projectInfo.displayName) {
            override fun actionPerformed(e: AnActionEvent) {
                println("Opening project: ${projectInfo.displayName}") // DEBUG LOG
                // Open the project using the original action
                projectInfo.originalAction.actionPerformed(e)
            }

            override fun update(e: AnActionEvent) {
                e.presentation.description = "Open project: ${projectInfo.path}"
            }
        }
    }
}