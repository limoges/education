// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
public final class Card implements Comparable {

  // Members
  private final Rank rank;
  private final Suit suit;

  // Methods
  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }

  // What is the relative value of a Joker?
  public int compareTo(Object o) {
    // TODO: standard value for different types
    if (!(o instanceof Card))
      return -1;
    
    Card c = (Card) o;

    if (this.rank == c.rank)
      return 0;
    if (this.rank > c.rank)
      return 1;
    
    return -1;
  }

  public boolean equals(Object o) {
    if (!(o instanceof Card)
      return false;

    Card c = (Card) o;

    if (this.rank != c.rank)
      return false;
    if (this.suit != c.suit)
      return false;

    return true;
  }

  public Suit getSuit() {
    return suit;
  }

  public Rank getRank() {
    return rank;
  }

  public String toString() {
    if (this.rank == Rank.Joker)
      return rank.toString();
    else
      return rank.toString() + " of " + suit.toString();
  }

}
