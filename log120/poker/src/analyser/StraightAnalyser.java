// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.analyser;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.ArrayList;

import poker.RequestHandAnalysis;
import poker.HandAnalyser;
import poker.cards.Card;
import poker.cards.Rank; 
import poker.cards.Suit; 
import poker.hands.PokerRank;

public class StraightAnalyser extends AbstractHandAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request))
      request.setPokerRank(PokerRank.Straight);
    else if (successor != null)
      successor.processRequest(request);
  }

  protected boolean analyseHand(RequestHandAnalysis request) {
    ArrayList<Card> cards = request.cards();
    return !HandAnalyser.hasStraight(cards).equals(Rank.None);
  }

}
