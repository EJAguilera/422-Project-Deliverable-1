package testDriver;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import testFile.TestDriverUtils;
import BCheck.ExpressionCheck;

public class TestDriverExpression {
	@Test
	void test() throws IOException, CheckstyleException {
		// Check setup
		DetailAST ast = JavaParser.parse(TestDriverUtils.GetFileContents("test/testFile/", "ExpressionTest.java"));
		ExpressionCheck check = new ExpressionCheck();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Traverse tree
		check.beginTree(ast);
		TestDriverUtils.Helper(check, ast, check.getRequiredTokens());
		double result = check.getExpressions();
		
		// Assertion
		System.out.println("Results posted: "+result);
		assertTrue(1.0 == result);
		System.out.println("Expression black-box check finished!");
	}
}
