package craft.magic

import groovy.io.FileType

class GeneratorExtension {
	def outputPath = "${ -> project.buildSrc}"
	def jsons = []

	def from(String inputDir) {
		new File(inputDir).eachFileRecurse (FileType.FILES) { file ->
			if (file.name.endsWith('.json')) {
		  		jsons << file
			}
		}
	}
}