package craft.magic

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.StringWriter
import java.nio.charset.Charset

import com.sun.codemodel.JDeclaration
import com.sun.codemodel.JExpression
import com.sun.codemodel.JGenerable
import com.sun.codemodel.JStatement
import com.sun.codemodel.JCodeModel
import com.sun.codemodel.JFormatter


class JCodeModelUtils {

	static String toString(def obj) {
		obj.toString()
	}

	public static String toString (JExpression aExpression)
	{
		StringWriter aSW = new StringWriter ();
		JFormatter formatter = new JFormatter (aSW);
		aExpression.generate (formatter);
		return aSW.toString ();
	}

	public static String toString (JDeclaration  declaration)
	{
		StringWriter aSW = new StringWriter ();
		JFormatter formatter = new JFormatter (aSW);
		declaration.declare (formatter);
		return aSW.toString ();
	}

}