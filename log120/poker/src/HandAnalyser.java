// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import java.util.Vector;
import poker.cards.Card;

public class HandAnalyser {

  public static void find(Iterator<Card> cards, EnumMap<Rank, int> seen) {
    seen.clear();
    while (cards.hasNext()) {
      Rank rank = cards.next().getRank();

      if (seen.containsKey(rank)) {
        int value = seen.get(rank);
        seen.put(rank, ++value);
      }
      else
        seen.put(rank, 1);
    }
  }

  public static void find(Iterator<Card> cards, EnumMap<Suit, int> seen) {
    seen.clear();
    while (cards.hasNext()) {
      Suit suit = cards.next().getSuit();

      if (seen.containsKey(suit)) {
        int value = seen.get(suit);
        seen.put(suit, ++value);
      }
      else
        seen.put(suit, 1);
    }
  }

  public static int consecutive(Vector<Card> cards) {
    Rank start;
    Iterator<Card> it = cards.iterator();

    while (it.hasNext()) {
      Card card = it.next();
      rank = card.getRank();

      while (rank
    }
  }

}
