package craft.magic

import org.junit.Test
import static org.junit.Assert.*
import static craft.magic.Utils.*
import com.sun.codemodel.JDeclaration

import static craft.magic.Utils.*

class mergerSourceTest {

	@Test
	void testMergeFileWithGeneratedObject() {
		def baseBuildJava = 'build/test/src/java/'
		def gen = new JavaClassGenerator()
        def name = 'com.test.provider.ClassOne.json'
        def jsonStr = this.getClass().getResource(name).text
        def res = gen.generateFromContent(jsonStr, name)

        MergerSource mergerSource = new MergerSource()
       	deleteFile(baseBuildJava + res.path() + '.java')
        mergerSource.outputFiles(baseBuildJava)

        def methodsWrited = mergerSource.generate(res)
        assertTrue(methodsWrited == 1)
	}

}