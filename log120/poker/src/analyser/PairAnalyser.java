// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.EnumMap;
import java.util.Iterator;
import poker.cards.Card;

public class PairAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.Pair);
    else if (successor != null)
      successor.processRequest(request);
  }

  protected boolean analyseHand(RequestHandAnalysis request) { 
    EnumMap<Rank, int> ranks = request.ranks();

    if (ranks.containsValue(2))
      return true;
    else if (request.shamAllowed() && ranks.containsValue(2 - request.shams()))
      return true;
    else
      return false;
  }

}
