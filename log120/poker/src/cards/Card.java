// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.cards;

import poker.ApplicationSupport;

public final class Card implements Comparable {

  private static String KW_RANK_OF_SUIT = "Keyword.RankOfSuit";
  static {
    KW_RANK_OF_SUIT = ApplicationSupport.getResource(KW_RANK_OF_SUIT);
  };

  // Members
  private final Rank rank;
  private final Suit suit;

  // Methods
  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }

  // What is the relative value of a Joker?
  public int compareTo(Object o) throws ClassCastException {
    if (!(o instanceof Card))
      throw new ClassCastException();
    
    Card c = (Card) o;

    return this.rank.compareTo(c.rank);
  }

  public boolean equals(Object o) {
    if (!(o instanceof Card))
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
      return rank.toString() +  KW_RANK_OF_SUIT + suit.toString();
  }

}
