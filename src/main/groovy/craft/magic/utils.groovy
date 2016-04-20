package craft.magic

import groovy.json.JsonBuilder

class Utils {
	static def ensurePath(String file) {
		ensurePath(file as File)
	}

	static def ensurePath(File file) {
	    if (!file.isDirectory() && !file.mkdirs()) {
	        throw new IOException('Could not create directory: ' + file)
	    }
	}
	static def printJson = { o -> println new JsonBuilder( o ).toPrettyString() }
	static def hasOne = { it?.size == 1 }
	static def deleteFile(String path) {
		def f = new File(path)
		if (f.exists()) f.delete()
	}
}