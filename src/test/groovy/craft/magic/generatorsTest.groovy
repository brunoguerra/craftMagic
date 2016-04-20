package craft.magic

import org.junit.Test
import static org.junit.Assert.*
import static craft.magic.Utils.*
import com.sun.codemodel.JDeclaration

class JavaClassGeneratorTest {

     @Test
    public void canConvertFileNameRight() {
        def gen = new JavaClassGenerator()
        def obj = GeneratedObject.newInstance(gen.className('a.b.c.extension'))

        assertTrue(obj.name == 'c')
    }

    @Test
    public void canParseOneFile() {
        def gen = new JavaClassGenerator()
        def name = 'com.test.provider.ClassOne.json'
        def jsonStr = this.getClass().getResource(name).text
        def res = gen.generateFromContent(jsonStr, name)

        assertTrue(res.name == 'ClassOne')
        def entry = res.methods.find { true }
        assertTrue(entry.key == 'executeQuery1')
        assertTrue(entry.value.method instanceof JDeclaration)
    }
}