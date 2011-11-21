// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.EnumMap;
import java.util.Iterator;
import poker.cards.Card;

public class TwoPairsAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.TwoPairsAnalyser);
    else if (successor != null)
      successor.processRequest(request);
  }

  protected boolean analyseHand(RequestHandAnalysis request) { 
    EnumMap<Rank, int> ranks = request.ranks();
    Set<Map.Entry<Rank, int>> counts = ranks.entrySet();

    int numberOfPairs = 0;
    int shamsLeft = request.shams();
    Iterator<Map.Entry<Rank, int>> it;
    for (it = counts.iterator(); it.hasNext();) {
      Map.Entry<Rank, int> entry = it.next();

      if (entry.getValue() == 2)
        ++numberOfPairs;
      // This is weird but it shouldn't happen since it should make a better case
      // We can assume if the sham is not used by this time, there is only one
      // and no other pair since it would make a ThreeOfAKind
      else if (request.shamAllowed() && shamsLeft != 0) {
        ++numberOfPairs;
        --shamsLeft;
      }
    }

    if (numberOfPairs >= 2)
      return true;
    else
      return false;
  }

}
