// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
public class RoyalStraightFlushAnalyser {

  public void processRequest(RequestHandAnalysis request) {
    if (analyseHand(request.getCardsInHand()) {
      request.setPokerRank(PokerRank.RoyalStraightFlush);
    }
    else if (successor != null)
      successor.processRequest(request);
  }

  private boolean analyseHand(Vector<Card> cards) { 
  }

}
