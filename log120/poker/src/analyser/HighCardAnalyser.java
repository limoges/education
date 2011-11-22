// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.analyser;

import poker.RequestHandAnalysis;
import poker.cards.Card;
import poker.cards.Rank; 
import poker.cards.Suit; 
import poker.hands.PokerRank; 

public class HighCardAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.HighCard);
  }

  protected boolean analyseHand(RequestHandAnalysis request) {
    return true;
  }
}
