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

                return recentProject
            }
        }
    }
}
