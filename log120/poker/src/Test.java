
import junit.textui.*;

class Test {

  public static void main(String[] args) {
    TestSuite tests = new TestSuite("Poker framework tests");

    tests.addTest(new TestSuite(CardTest.class));

    TestRunner.run(tests);
  }
}
