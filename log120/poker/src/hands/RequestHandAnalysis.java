// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.Vector;
import poker.cards.Card;

public class RequestHandAnalysis {

  // Members
  private final Hand hand;
  private PokerRank pokerRank;

  // Methods
  public RequestHandAnalysis(Hand hand) {
    this.hand = hand;
  }

  public Hand getHand() {
    return hand;
  }

  public Vector<Card> getCardsInHand() {
    return hand.getCards(); 
  }

  public PokerRank getPokerRank() {
    return pokerRank;
  }

  public void setPokerRank(PokerRank pokerRank) {
    this.pokerRank = pokerRank;
  }

}
