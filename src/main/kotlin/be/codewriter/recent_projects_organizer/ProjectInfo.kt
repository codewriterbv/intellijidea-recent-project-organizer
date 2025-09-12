package be.codewriter.recent_projects_organizer

import com.intellij.ide.ReopenProjectAction

data class ProjectInfo(
    val name: String,
    val displayName: String,
    val path: String,
    val originalAction: ReopenProjectAction
)
