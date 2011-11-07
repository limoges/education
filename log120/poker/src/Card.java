
public class Card {
  Rank rank;
  Suit suit;

  Card(Suits s, Ranks r) {
    this.suit = new Suit(s);
    this.rank = new Rank(r);
  }

  //TODO: check how to implement this...
  int compareTo(Object o) {

  }

  boolean equals(Object o) {
    if (o instanceof Card) {
      if (o.rank != this.rank)
        return false;
      if (o.suit != this.suit)
        return false;
      else
        return true;
    }
    else
      return false;
  }

  Suit getSuit() {
    return suit;
  }

  Rank getRank() {
    return rank;
  }

  String toString() {
    if (s.equals(new Rank(Ranks.Joker)))
      return rank.getName();
    else
      return rank.getName() + " of " + suit.getName();
  }
}
