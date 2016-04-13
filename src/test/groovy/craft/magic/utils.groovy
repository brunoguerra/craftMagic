package craft.magic

import groovy.json.JsonBuilder

class Utils {
	static def printJson = { o -> println new JsonBuilder( o ).toPrettyString() }
}