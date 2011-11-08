// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca

import java.util.Vector;

public abstract class AbstractHandAnalyser {
  protected AbstractHandAnalyser successor;

  public void setSuccessor(AbstractHandAnalyser successor) {
    this.successor = successor;
  }

  public abstract void processRequest(RequestHandAnalysis request);
  protected abstract boolean analyseHand(Vector<Card> cards);
}
