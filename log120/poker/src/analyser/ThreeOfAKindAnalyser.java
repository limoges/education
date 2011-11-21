// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.EnumMap;
import java.util.Set;
import java.util.Iterator;
import poker.cards.Card;

public class ThreeOfAKindAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.ThreeOfAKindAnalyser);
    else if (successor != null)
      successor.processRequest(request);
  }

  protected boolean analyseHand(RequestHandAnalysis request) { 
    EnumMap<Rank, int> ranks = request.ranks();
    Set<Map.Entry<Rank, int>> counts = ranks.entrySet();

    int numberOfThrees = 0;
    int shamsLeft = request.shams();
    Iterator<Map.Entry<Rank, int>> it;
    for (it = counts.iterator(); it.hasNext();) {
      Map.Entry<Rank, int> entry = it.next();

      if (entry.getValue() == 3)
        ++numberOfThrees;
      // We should have maximum of 2 shams
      else if (request.shamAllowed() && shamsLeft != 0) {
        int required = 3 - shamsLeft; // this should be 1
        if (entry.getValue() == (3 - shamsLeft)) {
          ++numberOfPairs;
          shamsLeft = 0;
        }
      }
    }

    if (numberOfThrees >= 1)
      return true;
    else
      return false;
  }

}
