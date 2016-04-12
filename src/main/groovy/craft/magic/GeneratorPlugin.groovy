package craft.magic

import org.gradle.api.Project
import org.gradle.api.Plugin

class GeneratorPlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('generator', type: GeneratorTask)
    }
}
