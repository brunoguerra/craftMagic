package craft.magic

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class GeneratorPluginTest {
    @Test
    public void generatorPluginAddsGeneratorTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'craft.magic.craftMagic'

        assertTrue(project.tasks.genClasses instanceof GeneratorTask)
    }

    @Test
    public void generatorPluginAddsGeneratorExtensionToProject() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'craft.magic.craftMagic'

        assertTrue(project.craftMagic.jsons instanceof ArrayList)
    }
}
