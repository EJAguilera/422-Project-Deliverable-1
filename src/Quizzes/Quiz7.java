package Quizzes;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractFileSetCheck;
import com.puppycrawl.tools.checkstyle.api.AutomaticBean;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.api.Context;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.ExternalResourceHolder;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.utils.TokenUtil;
import java.util.*;

public class Quiz7 {
	private void walkBlackBox(DetailAST ast, AbstractCheck check) {
		this.beginBlackBox(ast, check);
		this.processBlackBox(ast, check);
		this.finishBlackBox(ast, check);
	}
	
	private void beginBlackBox(DetailAST ast, AbstractCheck check) {
		check.beginTree(ast);
	}
	
	private void processBlackBox(DetailAST ast, AbstractCheck check) {
		DetailAST current = ast;
		while (current != null) {
			this.visitBlackBox(ast, check);
			DetailAST toVisit = current.getFirstChild();
			while (current != null && toVisit != null) {
				toVisit = current.getNextSibling();
				current = current.getParent();
			}
			current = toVisit;
		}
	}
	
	private void visitBlackBox(DetailAST ast, AbstractCheck check) {
		check.visitToken(ast);
	}
	
	private void finishBlackBox(DetailAST ast, AbstractCheck check) {
		check.finishTree(ast);
	}
}
