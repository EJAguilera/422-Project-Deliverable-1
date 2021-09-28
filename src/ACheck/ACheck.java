package ACheck;

import com.puppycrawl.tools.checkstyle.api.*;

public class ACheck extends AbstractCheck {
	
	public int operators = 0;
	public int operands = 0;

	@Override
	public int[] getDefaultTokens() {

		// Gets all tokens considered 'operators' by Checkstyle.
		return new int[] {
			TokenTypes.ASSIGN,		TokenTypes.BAND,		TokenTypes.BAND_ASSIGN,
			TokenTypes.BNOT,		TokenTypes.BOR,			TokenTypes.BOR_ASSIGN,
			TokenTypes.BSR,			TokenTypes.BSR_ASSIGN,	TokenTypes.BXOR,
			TokenTypes.BXOR_ASSIGN,	TokenTypes.COLON,		TokenTypes.COMMA,
			TokenTypes.DEC,			TokenTypes.DIV,			TokenTypes.DIV_ASSIGN,
			TokenTypes.DOT,			TokenTypes.EQUAL,		TokenTypes.GE,
			TokenTypes.GT,			TokenTypes.INC,			TokenTypes.INDEX_OP,
			TokenTypes.LAND,		TokenTypes.LE,			TokenTypes.LITERAL_INSTANCEOF,
			TokenTypes.LNOT,		TokenTypes.LOR,			TokenTypes.LT,
			TokenTypes.MINUS,		TokenTypes.MINUS_ASSIGN,TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN,	TokenTypes.NOT_EQUAL,	TokenTypes.PLUS,
			TokenTypes.PLUS_ASSIGN,	TokenTypes.POST_DEC,	TokenTypes.POST_INC,
			TokenTypes.QUESTION,	TokenTypes.SL,			TokenTypes.SR,
			TokenTypes.SR_ASSIGN,	TokenTypes.STAR,		TokenTypes.STAR_ASSIGN,
			TokenTypes.UNARY_MINUS,	TokenTypes.UNARY_PLUS
		};
	}
	
	@Override
	public void visitToken(DetailAST aAST) {
		// If operator is a special type, it has only one operand
		switch (aAST.getType()) {
			case TokenTypes.INC:
			case TokenTypes.POST_INC:
			case TokenTypes.DEC:
			case TokenTypes.POST_DEC:
			case TokenTypes.UNARY_MINUS:
			case TokenTypes.UNARY_PLUS:
				operands += 1;
			// Otherwise, it possess the standard two operands
			default:
				operands += 2;
		}
		// For every operator
		operators += 1;
	}

	@Override
	public int[] getAcceptableTokens() {

		// Gets all tokens considered 'operators' by Checkstyle.
		return new int[] {
			TokenTypes.ASSIGN,		TokenTypes.BAND,		TokenTypes.BAND_ASSIGN,
			TokenTypes.BNOT,		TokenTypes.BOR,			TokenTypes.BOR_ASSIGN,
			TokenTypes.BSR,			TokenTypes.BSR_ASSIGN,	TokenTypes.BXOR,
			TokenTypes.BXOR_ASSIGN,	TokenTypes.COLON,		TokenTypes.COMMA,
			TokenTypes.DEC,			TokenTypes.DIV,			TokenTypes.DIV_ASSIGN,
			TokenTypes.DOT,			TokenTypes.EQUAL,		TokenTypes.GE,
			TokenTypes.GT,			TokenTypes.INC,			TokenTypes.INDEX_OP,
			TokenTypes.LAND,		TokenTypes.LE,			TokenTypes.LITERAL_INSTANCEOF,
			TokenTypes.LNOT,		TokenTypes.LOR,			TokenTypes.LT,
			TokenTypes.MINUS,		TokenTypes.MINUS_ASSIGN,TokenTypes.MOD,
			TokenTypes.MOD_ASSIGN,	TokenTypes.NOT_EQUAL,	TokenTypes.PLUS,
			TokenTypes.PLUS_ASSIGN,	TokenTypes.POST_DEC,	TokenTypes.POST_INC,
			TokenTypes.QUESTION,	TokenTypes.SL,			TokenTypes.SR,
			TokenTypes.SR_ASSIGN,	TokenTypes.STAR,		TokenTypes.STAR_ASSIGN,
			TokenTypes.UNARY_MINUS,	TokenTypes.UNARY_PLUS
		};
	}

	@Override
	public int[] getRequiredTokens() {
		// Stubbed for compliance
		return new int[0];
	}
}
