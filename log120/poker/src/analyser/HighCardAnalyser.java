// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import poker.cards.Card;

public class HighCardAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.HighCard);
  }

  protected boolean analyseHand(RequestHandAnalysis request) {
    return true;
  }
}
