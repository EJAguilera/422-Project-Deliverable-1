package testDriver;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import testFile.TestDriverUtils;

import halsteadChecks.HalsteadEffortCheck;

public class TestDriverEffort {
	@Test
	void test() throws IOException, CheckstyleException {
		DetailAST ast = JavaParser.parse(TestDriverUtils.GetFileContents("test/testFile/", "EffortTest.java"));
		
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Traverse Tree
		check.beginTree(ast);
		TestDriverUtils.Helper(check, ast, check.getRequiredTokens());
		double result = check.getHalsteadEffort();
		
		// Assertions
		System.out.println("Results posted: "+result);
		assertTrue(42.0 == result);
		System.out.println("Halstead-Effort check black-box test finished!");
	}
}
