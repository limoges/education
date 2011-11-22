// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.analyser;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import poker.RequestHandAnalysis;
import poker.cards.Card;
import poker.cards.Rank; 
import poker.cards.Suit; 
import poker.hands.PokerRank; 

public class TwoPairsAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.TwoPairs);
    else if (successor != null)
      successor.processRequest(request);
  }

  protected boolean analyseHand(RequestHandAnalysis request) { 
    EnumMap<Rank, Integer> ranks = request.getRanks();
    Set<Map.Entry<Rank, Integer>> counts = ranks.entrySet();

    int numberOfPairs = 0;
    Iterator<Map.Entry<Rank, Integer>> it;
    for (it = counts.iterator(); it.hasNext();) {
      Map.Entry<Rank, Integer> entry = it.next();
      if (entry.getValue().equals(new Integer(2)))
        ++numberOfPairs;
    }

    return numberOfPairs >= 2;
  }

}
