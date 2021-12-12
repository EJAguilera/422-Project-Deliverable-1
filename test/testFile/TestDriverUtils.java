package testFile;

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

public class TestDriverUtils {
	// Get contents of a file
	public static FileContents GetFileContents(String path, String filename) throws IOException {
		File file = new File(path + filename);
		FileText fileText = new FileText(file, "UTF-8");
		return new FileContents(fileText);
	}
	
	// Takes check, ast node, and valid tokens, calls the visit-token method if visiting a valid token
	public static void Helper(AbstractCheck b, DetailAST a, int[] validTokens) {
		while(a != null) {
			if (ArrayUtils.contains(validTokens, a.getType())) {
				System.out.println("Visiting token with int type "+a.getType()+" and with text value "+a.getText());
				b.visitToken(a);
			}
			Helper(b, a.getFirstChild(), validTokens);
			a = a.getNextSibling();
		}
	}
}
