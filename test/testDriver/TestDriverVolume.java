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

// Checks
import halsteadChecks.HalsteadVolumeCheck;


public class TestDriverVolume {
	
	/*
	@Test
	public void testDriver(String path, String filename, AbstractCheck passedCheck) throws IOException, CheckstyleException {
		// Setup root from passed file, 
		// Note that location will usually only need '/test/testFile/fileName'
		DetailAST root = JavaParser.parse(this.getFileContents(path, filename));
		AbstractCheck check = passedCheck;
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize the check
		check.beginTree(root);
		
		// Iterate through tree
		helper(check, root, check.getRequiredTokens());
		
		// Complete tree
		check.finishTree(root);
		
		// Perform assertions here
		double results = check
	}
	*/
	private FileContents getFileContents(String path, String filename) throws IOException {
		File file = new File(path + filename);
		FileText fileText = new FileText(file, "UTF-8");
		return new FileContents(fileText);
	}
	
	
	@Test
	void test() throws IOException, CheckstyleException {
		// Build File
		FileContents fc = this.getFileContents("test/testFile/", "VolumeTest.java");
		
		// Checking if contents are read
		// System.out.println("File contents: "+ft.getFullText());
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check, root, check.getAcceptableTokens());
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		//for(LocalizedMessage lm : check.getMessages()) {
		//	System.out.println(lm.getMessage());
		//}
		
		double results = check.getVolume();
		
		// Verify Results
		assertTrue(results == 14.0);
		System.out.println("Halstead-Volume Check 1 Done!");
		
		// Testing the blank volume
		fc = this.getFileContents("test/testFile/", "BlankVolumeTest.java");
		root = JavaParser.parse(fc);
		
		check.beginTree(root);
		this.helper(check, root, check.getRequiredTokens());
		check.finishTree(root);
		
		results = check.getVolume();

		System.out.println("Results were: "+results);
		assertTrue(results == 0.0);
		System.out.println("Halstead-Volume Check 2 Done!");
		
	}
	
	// Takes check, ast node, and valid tokens, calls the visit-token method if visiting a valid token
	public void helper(AbstractCheck b, DetailAST a, int[] validTokens) {
		while(a != null) {
			if (ArrayUtils.contains(validTokens, a.getType())) {
				System.out.println("Visiting token with int type "+a.getType()+" and with text value "+a.getText());
				b.visitToken(a);
			}
			helper(b, a.getFirstChild(), validTokens);
			a = a.getNextSibling();
		}
	}
}
