package craft.magic

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class FindFilesTask extends DefaultTask {
    String initMessage = 'Finding generator descriptors...'

    @TaskAction
    def find() {
    	println initMessage
        // project.craftMagic.jsons << new File('default.nothing.json')
    }
}
