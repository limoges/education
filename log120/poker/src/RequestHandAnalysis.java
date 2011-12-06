// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

import poker.HandAnalyser;
import poker.hands.Hand;
import poker.hands.PokerRank;
import poker.cards.Rank;
import poker.cards.Suit;
import poker.cards.Card;

public class RequestHandAnalysis {

  // Members
  private Hand hand;
  private EnumMap<Rank, Integer> ranks;
  private EnumMap<Rank, ArrayList<Card>> cardsByRanks;
  private EnumMap<Suit, Integer> suits;
  private EnumMap<Suit, ArrayList<Card>> cardsBySuits;
  private boolean straight;

  // Methods
  public RequestHandAnalysis(Hand hand) {
    this.hand = hand;
    Collections.sort(this.cards(), Collections.reverseOrder());
    ranks = new EnumMap<Rank, Integer>(Rank.class);
    cardsByRanks = new EnumMap<Rank, ArrayList<Card>>(Rank.class);
    suits = new EnumMap<Suit, Integer>(Suit.class);
    cardsBySuits = new EnumMap<Suit, ArrayList<Card>>(Suit.class);
    HandAnalyser.cardsByRanks(this.cards(), ranks, cardsByRanks);
    HandAnalyser.cardsBySuits(this.cards(), suits, cardsBySuits);
    straight = !HandAnalyser.hasStraight(this.cards()).equals(Rank.None);
    flush = !HandAnalyser.hasFlush(this.cards()).equals(Suit.None);
  }

  public EnumMap<Rank, Integer> getRanks() {
    return ranks;
  }

  public EnumMap<Rank, ArrayList<Card>> getCardsByRanks() {
    return cardsByRanks;
  }

  public EnumMap<Suit, Integer> getSuits() {
    return suits;
  }

  public EnumMap<Suit, ArrayList<Card>> getCardsBySuits() {
    return cardsBySuits;
  }
  
  public boolean isStraight() {
    return straight;
  }

  public Suit isFlush() {
    Iterator<Map.Entry<Suit, Integer>> occurence = suits.entrySet().iterator();
    while (occurence.hasNext()) {
      Map.Entry<Suit, Integer> entry = occurence.next();
      if (entry.getValue() >= 5)
        return entry.getKey();
    }

    return Suit.None;
  }

  public ArrayList<Card> cards() {
    return hand.list();
  }

  public PokerRank getPokerRank() {
    return hand.getPokerRank();
  }

  public void setPokerRank(PokerRank pokerRank) {
    hand.setPokerRank(pokerRank);
  }

}
