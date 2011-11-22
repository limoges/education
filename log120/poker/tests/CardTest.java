import junit.framework.*;

import poker.cards.*;

public class CardTest extends TestCase {

  private Card c1, c2, c3, c4, c5, c6;

  public void setUp() {
    c1 = new Card(Suit.Hearts, Rank.Ace);  
    c2 = new Card(Suit.Hearts, Rank.Ace);  
    c3 = new Card(Suit.Hearts, Rank.Two);  
    c4 = new Card(Suit.Clubs, Rank.Two);

    for (Suit s : Suit.values()) {
      System.out.printf("Suit %s is %f%n", s, s.getName());
    }
  }

  public void tearDown() {
    c1 = null; 
    c2 = null; 
    c3 = null; 
    c4 = null; 
  }

  public void testEquals() {
    // Self
    assertTrue(c1.equals(c1));
    assertTrue(c1.equals(c2));
    // Equal
    assertEquals(c1.equals(c2), c2.equals(c1));
    assertEquals(false, c2.equals(c3));
    assertEquals(false, c3.equals(c4));
  }

  public void testCompareTo() {
    assertTrue( c1.compareTo(c2) == 0 );
    assertTrue( c2.compareTo(c3)  > 0 );
    assertTrue( c3.compareTo(c2)  < 0 );
  }

  public void testToString() {
    c5 = new Card(Suit.None, Rank.Joker);

    assertEquals(Rank.Joker.toString(), c5.toString());
    assertEquals(Rank.Ace.toString() + " of " + Suit.Hearts.toString(),
        "Ace of Hearts");
  }
}
