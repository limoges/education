// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
public abstract class AbstractHandAnalyser {
  protected AbstractHandAnalyser successor;

  public void setSuccessor(AbstractHandAnalyser successor) {
    this.successor = successor;
  }

  private abstract boolean analyseHand(Vector<Card> cards);
}
