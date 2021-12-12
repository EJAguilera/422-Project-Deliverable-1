package testFile;

public class VariableDeclarationTest {
	void func() { 
		// Initializations, should not be counted
		int a, b, c;
		
		// Declarations, should be counted
		a = 4;
		b = 5;
		c = 6;
	}
}
