package craft.magic

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class GeneratorPluginTest {
    @Test
    public void pluginAddsGeneratorTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'craft.magic.craftMagic'

        assertTrue(project.tasks.genClasses instanceof GeneratorTask)
    }

    @Test
    public void pluginAddsGeneratorExtensionToProject() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'craft.magic.craftMagic'

        assertTrue(project.craftMagic.jsons instanceof ArrayList)
    }

    @Test
    public void pluginAddsInputPathWithJsonFiles() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'craft.magic.craftMagic'

        project.craftMagic.from('.')

        assertTrue(project.craftMagic.jsons instanceof ArrayList)
        assertFalse(project.craftMagic.jsons?.empty)
    }


}
