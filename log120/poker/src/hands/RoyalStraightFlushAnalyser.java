// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.Vector;
import poker.cards.Card;

public class RoyalStraightFlushAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request.getCardsInHand())) {
      request.setPokerRank(PokerRank.RoyalStraightFlush);
    }
    else if (successor != null)
      successor.processRequest(request);
  }

  protected boolean analyseHand(Vector<Card> cards) { 
    return true;
  }

}
