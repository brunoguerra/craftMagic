package craft.magic

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class GeneratorTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('xYz', type: GeneratorTask)
        assertTrue(task instanceof GeneratorTask)
    }
}
