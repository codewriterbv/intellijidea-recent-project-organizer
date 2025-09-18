package be.codewriter.recent_projects_organizer.be.codewriter.recent_projects_organizer

import be.codewriter.recent_projects_organizer.OrganizedRecentProjectsSubmenu
import kotlin.test.Test
import kotlin.test.assertEquals

class OrganizedRecentProjectsSubmenuTest {

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