package testDriver;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
// import com.puppycrawl.tools.checkstyle
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import testFile.TestDriverUtils;

import BCheck.GetCommentsCheck;

public class TestDriverComments {
	@Test
	void test() throws IOException, CheckstyleException {
		// Check setup
		DetailAST ast = JavaParser.parse(TestDriverUtils.GetFileContents("test/testFile/", "CommentsTest.java"));
		GetCommentsCheck check = new GetCommentsCheck();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Comment"));
		check.contextualize(new DefaultContext());
		
		// Traverse Tree
		check.beginTree(ast);
		TestDriverUtils.Helper(check, ast, check.getRequiredTokens());
		double result = check.getCommentsCount();
		
		// Assertion
		System.out.println("Results posted: "+result);
		assertTrue(3.0 == result);
		System.out.println("Get-Comments black-box check finished!");
	}
}
