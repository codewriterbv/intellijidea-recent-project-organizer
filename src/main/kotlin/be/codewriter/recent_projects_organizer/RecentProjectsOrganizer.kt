package be.codewriter.recent_projects_organizer

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class RecentProjectsOrganizer: ProjectActivity {
    override suspend fun execute(project: Project) {
        println("Recent Projects Organizer plugin loaded for project: ${project.name}")

        // The actual functionality is now handled by tools which are registered in plugin.xml
    }
}