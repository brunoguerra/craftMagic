package craft.magic

import static craft.magic.Utils.*

class MergerSource {
	def target
	def content
	def lastLine = -1
	def methodsWrited = 0
	def outputFiles
	def extension = '.java'

	def generate(GeneratedObject generatedObject) {
		this.target = generatedObject
		def file = file()
		if (file.exists()) {
			content = file.readLines()

			findLastLine()

			content.removeAt(lastLine)
			
			writeMethods()
			writeLastLine()
			writeFile(content.join("\n"))
		} else {
			methodsWrited = target.methods?.size()
			writeFile(target.toString())
		}

		methodsWrited
	}

	def writeMethods() {
		if (target.methods?.size() > 0) {
            target.methods.values().each { if (!methodExists(it)) writeMethod(it) }
        }
	}

	def findLastLine() {
		def lastKey = /\}/
		def lineNo = content.size()
		try {
			content.reverseEach {
				if (it=~lastKey) {
					lastLine = lineNo-1
					throw Exception()
				}
				lineNo--
			}
		} catch (ex) { }

		if (lastLine == -1) { throw new Exception("No last line for ${target.uri()}") }
	}

	def writeLastLine() {
		content.add("}")
	}

	def outputFiles(String outputFiles) {
		this.outputFiles = outputFiles
	}

	def file() {
		outputFiles + this.target.uri() + '/' + this.target.name + extension as File
	}

	def methodExists(method) {
		content.grep(~".*${method.sign()}.*").size()
	}

	def writeMethod(method) {
		content.addAll(JCodeModelUtils.toString(method.method).tokenize("\n"))
		methodsWrited++
	}

	def writeFile(content) {
		def file = file()
		println file
		ensurePath(file.getParentFile())
		if (!file.exists()) { file.createNewFile() }
		file.write(content)
	}
}