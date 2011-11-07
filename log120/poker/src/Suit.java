
public enum Suits implements Comparable {
  // Possible values of an enum
  None, Club, Diamond, Heart, Spade;

  private String
    NONE = "suit.none",
    CLUBS = "suit.clubs",
    DIAMONDS = "suit.diamonds",
    HEARTS = "suit.hearts",
    SPADES = "suit.spades";
}

public class Suit implements Comparable {
  Suits suit;
  
  Suit(Suits value) {
    this.suit = value;
  } 

  boolean equals(Object o) {
    if (o instanceof Suit)
      return false;
    if (o.suit == this.suit)
      return true;
    else
      return false;
  }

  //TODO: this function fails hard.
  int compareTo(Object o) {
    System.err.println("Should fix suit.compareTo before using");
    if (o instanceof Suit) {
      if (o.suit == this.suit)
        return 0;
      else
        return 1;
    }
    else
      return -1;
  }

  String getName() {
    return suit.toString();
  }        

  String toString() {
    return suit.toString();
  }       
}
