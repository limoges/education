// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.Vector;
import poker.cards.Card;

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

    return this.pokerRank.compareTo(h.pokerRank);
  }

  public void setPokerRank(PokerRank pokerRank) {
    this.pokerRank = pokerRank;
  }

  public Vector<Card> getCards() {
    return cards;
  }
}
