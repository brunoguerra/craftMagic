package craft.magic

import groovy.json.JsonSlurper

import com.sun.codemodel.ClassType
import com.sun.codemodel.JCodeModel
import com.sun.codemodel.JExpr
import com.sun.codemodel.JDefinedClass

import static com.sun.codemodel.JMod.*
import static craft.magic.Utils.*

class JavaClassGenerator {
	JCodeModel cm = new JCodeModel()

	def generateFromFiles(File[] inputFiles) {
    	inputFiles.each { 
    		println "generating: $it"
    		generateFromJSON(it)
    	}
    }

    def save(File file) {
    	ensurePath(file)
    	cm.build(file)
    }

    def generateFromJSON(file) {
        generateFromContent(file.text, file.name)
    }

    def generateFromContent(content, fileName) {
        def obj = new GeneratedObject(className(fileName))
        obj.json = new JsonSlurper().parseText(content)
        obj.cls = cm._class(PUBLIC | FINAL, obj.fqcn(), ClassType.CLASS)
        new GenerateBody(obj)
        println obj.uri()
        obj
    }

    def className(fileName) {
        def pkg = fileName.tokenize "\\."
        def ext = pkg.pop()
        def name = pkg.pop()
        return [pkg: pkg.join('.'), name: name]
    }

    // Scoped classes
    // -----------------------------------------------------------------------    

}

class GeneratedObject {
    String name
    String pkg
    def methods = [:],
        json,
        cls

    def fqcn() {
        pkg + '.' + name
    }

    def add(GenerateMethod method) {
        methods[method.name] = method
    }

    def uri() {
        println 'URI Called:'
        pkg.split('\\.').join('/')  
    }

    def path() {
        uri() + '/' + name
    }

    String toString() {
        JCodeModelUtils.toString(this.cls)
    }
}

class GenerateBody {
    GenerateBody(obj) {
        if (obj.json.methods?.size > 0) {
            obj.json.methods.each { obj.add(new GenerateMethod(obj.cls, it)) }
        }
    }
}

class GenerateMethod {
    def cls, 
        spec, 
        name, 
        returnType,
        method

    def parseType = { cm, str -> cm.parseType(str) }
    Closure whatIs

    GenerateMethod(cls, spec) {
        this.cls = cls
        this.spec = spec
        this.name = spec.name

        whatIs = parseType.curry(cls.owner())

        returnType = whatIs(spec.type)
        method = cls.method(PUBLIC, returnType, spec.name)
        
        theParams()
        theBody()
    }

    def theParams() {
        if (spec.params?.size > 0) {
            spec.params.each { method.param(whatIs(it.type), it.name) }
        }
    }

    def theBody() {
        def b = method.body()
        spec.body.each { 
            b.directStatement(it.plain)
        }
    }

    def sign() {
        spec.type + ' ' + name
    }

}

