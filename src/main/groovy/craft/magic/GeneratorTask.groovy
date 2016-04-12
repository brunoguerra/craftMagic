package craft.magic

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GeneratorTask extends DefaultTask {
    String initMessage = 'Initializing generator...'

    @TaskAction
    def generate() {
        println initMessage
    }
}
