import junit.textui.TestRunner;

public class Tests {

  public void main(String[] args) {
    TestSuite tests = new TestSuite("Tests for the poker framework");
    tests.addTest(new TestSuite(CardTest.class));
    tests.addTest(new TestSuite(HandTest.class));
    tests.addTest(new TestSuite(RankTest.class));

    TestRunner.run(tests);
  }

}
