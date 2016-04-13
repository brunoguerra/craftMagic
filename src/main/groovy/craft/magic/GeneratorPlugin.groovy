package craft.magic

import org.gradle.api.Project
import org.gradle.api.Plugin

class GeneratorPlugin implements Plugin<Project> {
    void apply(Project target) {
    	target.extensions.create('craftMagic', GeneratorExtension)

    	def findFilesTask = target.task('genFind', type: FindFilesTask)
        def generatorTask = target.task('genClasses', type: GeneratorTask)

        generatorTask.dependsOn findFilesTask
    }
}
