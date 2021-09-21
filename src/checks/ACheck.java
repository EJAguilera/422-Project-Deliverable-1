package checks;

import com.puppycrawl.tools.checkstyle.api.*;

public class ACheck extends AbstractCheck {
	
	private int operatorsCount = 0;
	private int operandsCount = 0;
	private int halsteadLength = 0;

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
					// Find and count every possible operator token
					TokenTypes.ASSIGN,
					TokenTypes.BAND,
					TokenTypes.BAND_ASSIGN,
					TokenTypes.BNOT,
					TokenTypes.BOR,
					TokenTypes.BOR_ASSIGN,
					TokenTypes.BSR,
					TokenTypes.BSR_ASSIGN,
					TokenTypes.BXOR,
					TokenTypes.BXOR_ASSIGN,
					TokenTypes.COLON,
					TokenTypes.COMMA,
					TokenTypes.DEC,
					TokenTypes.DIV,
					TokenTypes.DIV_ASSIGN,
					TokenTypes.DOT,
					TokenTypes.EQUAL,
					TokenTypes.GE,
					TokenTypes.GT,
					TokenTypes.INC,
					TokenTypes.INDEX_OP,
					TokenTypes.LAND,
					TokenTypes.LE,
					TokenTypes.LITERAL_INSTANCEOF,
					TokenTypes.LNOT,
					TokenTypes.LOR,
					TokenTypes.LT,
					TokenTypes.MINUS,
					TokenTypes.MINUS_ASSIGN,
					TokenTypes.MOD,
					TokenTypes.MOD_ASSIGN,
					TokenTypes.NOT_EQUAL,
					TokenTypes.PLUS,
					TokenTypes.PLUS_ASSIGN,
					TokenTypes.POST_DEC,
					TokenTypes.POST_INC,
					TokenTypes.QUESTION,
					TokenTypes.SL,
					TokenTypes.SL_ASSIGN,
					TokenTypes.SR,
					TokenTypes.SR_ASSIGN,
					TokenTypes.STAR,
					TokenTypes.STAR_ASSIGN,
					TokenTypes.UNARY_MINUS,
					TokenTypes.UNARY_PLUS
				};
	}
	
	@Override
	public void visitToken(DetailAST ast) {		
		
		// For conditionals
		int tokenType = ast.getType();
		
		// Increment operator count by 1
		this.operatorsCount++;
		
		if (
				tokenType == TokenTypes.INC || 
				tokenType == TokenTypes.DEC || 
				tokenType == TokenTypes.UNARY_MINUS || 
				tokenType == TokenTypes.UNARY_PLUS ||
				tokenType == TokenTypes.COLON
			) 
		{
			// If this is an atypical operator, increment the operands count by only one
			this.operandsCount += 1;
		} else {
			// This is a typical operator, which will have two operands
			this.operandsCount += 2;
		}
		
		// Sum operation for final Halstead Length
		this.halsteadLength = this.operatorsCount + this.operandsCount;
		
		String logMsg = "EJA - ACheck found operator with operands, Halstead length now: " + this.halsteadLength;
		
		log(ast.getLineNo(), ast.getColumnNo(), logMsg);
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	// Unsure if this is how this function is supposed to be used
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {
				// Find and count every possible operator token
				TokenTypes.ASSIGN,
				TokenTypes.BAND,
				TokenTypes.BAND_ASSIGN,
				TokenTypes.BNOT,
				TokenTypes.BOR,
				TokenTypes.BOR_ASSIGN,
				TokenTypes.BSR,
				TokenTypes.BSR_ASSIGN,
				TokenTypes.BXOR,
				TokenTypes.BXOR_ASSIGN,
				TokenTypes.COLON,
				TokenTypes.COMMA,
				TokenTypes.DEC,
				TokenTypes.DIV,
				TokenTypes.DIV_ASSIGN,
				TokenTypes.DOT,
				TokenTypes.EQUAL,
				TokenTypes.GE,
				TokenTypes.GT,
				TokenTypes.INC,
				TokenTypes.INDEX_OP,
				TokenTypes.LAND,
				TokenTypes.LE,
				TokenTypes.LITERAL_INSTANCEOF,
				TokenTypes.LNOT,
				TokenTypes.LOR,
				TokenTypes.LT,
				TokenTypes.MINUS,
				TokenTypes.MINUS_ASSIGN,
				TokenTypes.MOD,
				TokenTypes.MOD_ASSIGN,
				TokenTypes.NOT_EQUAL,
				TokenTypes.PLUS,
				TokenTypes.PLUS_ASSIGN,
				TokenTypes.POST_DEC,
				TokenTypes.POST_INC,
				TokenTypes.QUESTION,
				TokenTypes.SL,
				TokenTypes.SL_ASSIGN,
				TokenTypes.SR,
				TokenTypes.SR_ASSIGN,
				TokenTypes.STAR,
				TokenTypes.STAR_ASSIGN,
				TokenTypes.UNARY_MINUS,
				TokenTypes.UNARY_PLUS
			};
	}

}
