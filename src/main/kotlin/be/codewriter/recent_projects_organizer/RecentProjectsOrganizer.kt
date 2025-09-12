package be.codewriter.recent_projects_organizer

import com.intellij.ide.RecentProjectListActionProvider
import com.intellij.ide.ReopenProjectAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class RecentProjectsOrganizer: ProjectActivity {
    override suspend fun execute(project: Project) {
        RecentProjectListActionProvider
            .getInstance()
            .getActions(addClearListItem = false, useGroups = false)
            .forEach {
                val recentProject: ReopenProjectAction = it as ReopenProjectAction

                // TODO: Add your logic to organize recent projects here
                // For example, you could sort, filter, or modify the recent projects list
                println("Found recent project: ${recentProject.projectPath}")
            }
    }
}
