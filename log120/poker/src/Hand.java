// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
public class Hand implements Comparable {

  // Members
  PokerRank pokerRank;
  Vector<Card> cards;

  // Methods
  public Hand() {}

  public void add(Card card) {
    cards.add(card);
  }

  public int compareTo(Object o) {
    if (!(o instanceof Hand))
      return -1;

    Hand h = (Hand) o;

    if (this.pokerRank == h.pokerRank)
      return 0;
    if (this.pokerRank > h.pokerRank)
      return 1;
    
    return -1;
  }

}
