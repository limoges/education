// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.analyser;

import java.util.EnumMap;
import java.util.Iterator;
import poker.RequestHandAnalysis;
import poker.cards.Card;
import poker.cards.Suit;
import poker.cards.Rank;
import poker.hands.PokerRank;

public class FlushAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.Flush);
    else if (successor != null)
      successor.processRequest(request);
  }

  protected boolean analyseHand(RequestHandAnalysis request) {
    return !request.isFlush().equals(Suit.None);
  }

}
