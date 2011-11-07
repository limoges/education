
public enum PokerRank {

  HighCard(1, HIGH_CARD),
  Pair(2, PAIR),
  TwoPair(3, TWO_PAIR),
  ThreeOfAKind(4, THREE_OF_A_KIND),
  Straight(5, STRAIGHT),
  Flush(6, FLUSH),
  FullHouse(7, FULL_HOUSE),
  FourOfAKind(8, FOUR_OF_A_KIND),
  StraightFlush(9, STRAIGHT_FLUSH),
  FiveOfAKind(10, FIVE_OF_A_KIND),
  RoyalStraightFlush(11, ROYAL_STRAIGHT_FLUSH);

  private final int value;
  private final String name;

  private PokerRank(int value, String name) {
    this.value = value;
    this.name = ApplicationSupport.getResource(name);
  }

  private String
    HIGH_CARD = "poker_ranks.high_card",
    PAIR = "poker_ranks.pair",
    TWO_PAIR = "poker_ranks.two_pair",
    THREE_OF_A_KIND = "poker_ranks.three_of_a_kind",
    STRAIGHT = "poker_ranks.straight",
    FLUSH = "poker_ranks.flush",
    FULL_HOUSE = "poker_ranks.full_house",
    FOUR_OF_A_KIND = "poker_ranks.four_of_a_kind",
    STRAIGHT_FLUSH = "poker_ranks.straight_flush",
    FIVE_OF_A_KIND = "poker_ranks.five_of_a_kind",
    ROYAL_STRAIGHT_FLUSH = "poker_ranks.royal_straight_flush";

}
