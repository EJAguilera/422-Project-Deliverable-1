package testFile;

public class LoopingStatementTest {
	void func() {
		int a = 0;
		while (a < 10) {
			a++;
			int b = 0;
			b += 1;
		}
	}
}
