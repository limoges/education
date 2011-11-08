import junit.framework.*;

public class HandTest extends TestCase {
  
  private Hand h1, h2, h3;

  public void setUp() {
    h1 = new Hand();
    h1.setPokerRank(PokerRank.RoyalStraightFlush);
    h2 = new Hand();
    h2.setPokerRank(PokerRank.RoyalStraightFlush);
    h3 = new Hand();
    h3.setPokerRank(PokerRank.HighCard);
  }

  public void tearDown() {
    h1 = null;
    h2 = null;
    h3 = null;
  }

  public void testCompareTo() {
    assertTrue( h1.compareTo(h1) == 0 );
    assertTrue( h1.compareTo(h2) == 0 );
    assertTrue( h1.compareTo(h3) > 0 );
    assertTrue( h3.compareTo(h1) < 0 );
  }

}
