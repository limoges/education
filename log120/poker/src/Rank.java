
public enum Rank implements Comparable {
  // Possible values in French playing cards
  Joker(0, JOKER),
  Ace(14, ACE),
  Two(2, TWO),
  Three(3, THREE),
  Four(4, FOUR),
  Five(5, FIVE),
  Six(6, SIX),
  Seven(7, SEVEN),
  Eight(8, EIGHT),
  Nine(9, NINE),
  Ten(10, TEN),
  Jack(11, JACK),
  Queen(12, QUEEN),
  King(13, KING);

  private String
    JOKER = "ranks.joker",
    ACE = "ranks.ace",
    TWO = "ranks.two",
    THREE = "ranks.three",
    FOUR = "ranks.four",
    FIVE = "ranks.five",
    SIX = "ranks.six",
    SEVEN = "ranks.seven",
    EIGHT = "ranks.eight",
    NINE = "ranks.nine",
    TEN = "ranks.ten",
    JACK = "ranks.jack",
    QUEEN = "ranks.queen",
    KING = "ranks.king";

  Rank(int value, String name) {
    this.value = value;
    this.name = ApplicationSupport.getResource(name);
  }

  char caracterOnCard() {
    switch (rank) {
      case Joker: return 'J';
      case Ace: return 'A';
      case Two: return '2';
      case Three: return '3';
      case Four: return '4';
      case Five: return '5';
      case Six: return '6';
      case Seven: return '7';
      case Eight: return '8';
      case Nine: return '9';
      case Ten: return 'T';
      case Jack: return 'J';
      case Queen: return 'Q';
      case King: return 'K';
      default :
        System.err.println("characterOnCard failed");
      break;
    }
  }

  String getName() {
    return rank;
  }

  String toString() {
    return rank.toString();
  }

  //TODO: this function fails hard
  int compareTo(Object o) {
    System.err.println("Should fix suit.compareTo before using");
    if (o instanceof Rank) {
      if (o.rank == this.rank)
        return true;
      else
        return false;
    }
    else
      return false;
  }

  boolean equals(Object o) {
    if (o instanceof Rank)
      return false;
    if (o.rank == this.rank)
      return true;
    else
      return false;
  }

}
