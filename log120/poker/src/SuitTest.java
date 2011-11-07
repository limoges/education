
import junit.framework.*;

public class CardTest extends TestCase {
  private static Card card;

  public void testJoker() {
    card = null;
    card = new Card(Suits.None, Ranks.Joker);
    String result = card.getName();
    assertEquals(result, "Joker");
  }

  public void testName() {
    card = null;
    card = new Card(Suits.Heart, Ranks.Ace);
    String result = card.toString();
    assertEquals(result, "Ace of Heart");
  }

  public void testCompareTo() {
    card = null;


  }

  public void testEquals() {
    card = new Card(Suits.Club, Ranks.Three);
    Card card2 = new Card(Suits.Club, Ranks.Three);
    Card card3 = new Card(Suits.Club, Ranks.Three);
    boolean result, result2, result3;

    // reflexive and symmetric
    result = card.equals(card2);
    assertEquals(result, true);
    result2 = card2.equals(card);
    assertEquals(result2, true);

    // transitive
    result3 = card2.equals(card3);
    assertEquals(result3, true);
    assertEquals(card.equals(card3), true);
  }


}
