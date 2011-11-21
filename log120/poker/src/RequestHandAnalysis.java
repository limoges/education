// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.ArrayList;
import poker.cards.Card;

public class RequestHandAnalysis {

  // Members
  private final Hand hand;
  private PokerRank pokerRank;
  private EnumMap<Rank, int> ranks;
  private EnumMap<Suit, int> suits;
  private int consecutives;
  private boolean shamAllowed;
  private int shams;

  // Methods
  // Post condition :
  // Sorted ascending
  // Contains no sham
  public RequestHandAnalysis(Hand hand) {
    this.hand = hand;
    Collections.sort(hand.getCards());
    ranks = new EnumMap<Rank, int>();
    suits = new EnumMap<Suit, int>();
    find(cards(), ranks);
    find(cards(), suits);
    consecutive = consecutives(cards());
    shamAllowed = true;
    if (ranks.containsKey(Rank.Joker)) {
      shams = ranks.get(Rank.Joker);
      ranks.remove(Rank.Joker);
    }
    else
      shams = 0;
  }

  public int shams() {
    return shams
  }

  public Iterator<Card> cards() {
    return hand.iterator(); 
  }

  public PokerRank getPokerRank() {
    return pokerRank;
  }

  public void setPokerRank(PokerRank pokerRank) {
    this.pokerRank = pokerRank;
  }

}
