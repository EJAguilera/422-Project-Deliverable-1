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

import halsteadChecks.HalsteadVocabularyCheck;

public class TestDriverVocabulary {
	@Test
	void test() throws IOException, CheckstyleException {
		// Setup
		DetailAST ast = JavaParser.parse(TestDriverUtils.GetFileContents("test/testFile/", "VocabularyTest.java"));
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		

		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Traverse tree
		check.beginTree(ast);
		TestDriverUtils.Helper(check, ast, check.getRequiredTokens());
		double result = check.getUniqueOperatorsOperands();
		
		// Results posted
		System.out.println("Results posted: "+result);
		assertTrue(result == 5);
		System.out.println("Halstead Vocabulary black-box test finished!");
	}
}
