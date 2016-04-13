package craft.magic

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GeneratorTask extends DefaultTask {
    String initMessage = 'Generating classes...'

    File outputDir() {
    	project.craftMagict.outputDir
    }

    File[] inputFiles() {
        project.craftMagic.jsons
    }

    @TaskAction
    def generate() {
        println initMessage

        def gen = new JavaClassGenerator()
                        .loadFromFiles(inputFiles())
                        .save(outputDir())

        println "Generated ${inputFiles().size}"
    }

    
}
