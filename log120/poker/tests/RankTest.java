import junit.framework.*;

import poker.cards.Rank;

public class RankTest extends TestCase {

  private Rank r1;

  public void setUp() {
    r1 = Rank.Joker;
  }

  public void tearDown() {
    r1 = null;
  }

  public void testSymbol() {
    assertEquals('0', r1.getSymbol());
  }
}
