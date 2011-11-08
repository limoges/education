import junit.framework.*;

public class HandTest extends TestCase {
  
  private Hand h1, h2, h3;

  public void setUp() {
    h1 = new Hand();
    h1.setPokerRank(RoyalStraightFlush);
    h2 = new Hand();
    h2.setPokerRank(RoyalStraightFlush);
    h3 = new Hand();
    h3.setPokerRank(HighCard);
  }

  public void tearDown() {
    h1 = null;
    h2 = null;
    h3 = null;
  }

  public void testCompareTo() {
    assertEquals(0, h1.compareTo(h1));
    assertEquals(0, h1.compareTo(h2));
    assertEquals(1, h1.compareTo(h3));
    assertEquals(-1, h3.compareTo(h1));
  }

}
