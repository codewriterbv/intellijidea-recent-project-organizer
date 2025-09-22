package be.codewriter.recent_projects_organizer.be.codewriter.recent_projects_organizer

import be.codewriter.recent_projects_organizer.OrganizedRecentProjectsSubmenu
import be.codewriter.recent_projects_organizer.ProjectInfo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OrganizedRecentProjectsSubmenuTest {

    @Test
    fun shouldCorrectlySortProjects() {
        val submenu = OrganizedRecentProjectsSubmenu()

        val recentProjects = listOf(
            ProjectInfo("", "aaa z", "/home/project/aaa-z"),
            ProjectInfo("", "aaa 12", "/home/project/aaa-12"),
            ProjectInfo("", "AAA 9", "/home/project/aaa-9"),
            ProjectInfo("", "bbb 9", "/home/project/bbb-1"),
            ProjectInfo("", "Bbb 1", "/home/project/bbb-9"),
            ProjectInfo("", "aaa", "/home/project/aaa")
        )

        var organized = submenu.organizeProjectsByFirstWord(recentProjects)

        assertEquals(2, organized.size)

        // First group should have key "aaa" and contain 4 projects
        assertTrue(organized.containsKey("aaa"))
        assertEquals(4, organized["aaa"]?.size)

        // Second group should have key "bbb" and contain 2 projects
        assertTrue(organized.containsKey("bbb"))
        assertEquals(2, organized["bbb"]?.size)

        // Check sorting of projects in each group
        assertEquals("Bbb 1", organized["bbb"]?.get(0)?.displayName, "First in bbb")
        assertEquals("bbb 9", organized["bbb"]?.get(1)?.displayName, "Second in bbb")
    }

    @Test
    fun testGetFirstWord() {
        val submenu = OrganizedRecentProjectsSubmenu()

        assertEquals("Project", submenu.getFirstWord("Project-123"))
        assertEquals("My", submenu.getFirstWord("My Project"))
        assertEquals("TestProject", submenu.getFirstWord("TestProject"))
        assertEquals("Test", submenu.getFirstWord("Test_Pro ject "))
        assertEquals("Test", submenu.getFirstWord("Test-Pro ject"))
        assertEquals("", submenu.getFirstWord(""))
        assertEquals("The", submenu.getFirstWord("The-Amazing-Project"))
        assertEquals("123", submenu.getFirstWord("123-Project"))
        assertEquals("123", submenu.getFirstWord("123.Project"))
    }
}