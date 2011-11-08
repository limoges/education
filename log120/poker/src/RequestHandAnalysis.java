// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
public class RequestHandAnalysis {

  // Members
  private final Hand hand;
  private final PokerRank pokerRank;

  // Methods
  public RequestHandAnalysis(Hand hand) {
    this.hand = hand;
  }

  public Hand getHand() {
    return hand;
  }

  public Vector<Card> getCardsInHand() {
    return hand.getCards(); 
  }

  public PokerRank getPokerRank() {
    return pokerRank;
  }

  public void setPokerRank(PokerRank pokerRank) {
    this.pokerRank = pokerRank;
  }

}
