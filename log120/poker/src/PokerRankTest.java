
public class PokerRankTest {

  public void testCompareTo() {
    PokerRank pr1 = PokerRank.HighCard;
    PokerRank pr2 = PokerRank.HighCard;
    PokerRank pr3 = PokerRank.HighCard;
    PokerRank pr4 = PokerRank.Straight;

    assertEquals(pr1.compareTo(pr2), pr2.compareTo(pr1));

    boolean r1 = pr1.compareTo(pr2);
    boolean r2 = pr2.compareTo(pr3);
    boolean r3 = r1 == r2;
    
    assertEquals(r1, true);
  }

}
