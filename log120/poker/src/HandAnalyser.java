// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;

import poker.cards.Card;
import poker.cards.Suit;
import poker.cards.Rank;

public class HandAnalyser {

  public static void cardsByRanks(ArrayList<Card> hand, EnumMap<Rank, Integer> count, EnumMap<Rank, ArrayList<Card>> list) {
    count.clear();
    list.clear();
    Iterator<Card> cards = hand.iterator();

    while (cards.hasNext()) {
      Card card = cards.next();
      Rank rank = card.getRank();
      ArrayList<Card> values;
      Integer value;

      if (count.containsKey(rank)) {
        value = count.get(rank);
        values = list.get(rank);
      }
      else {
        value = new Integer(1);
        values = new ArrayList<Card>();
      }
      values.add(card);
      ++value;
      count.put(rank, value);
      list.put(rank, values);
    }
  }

  public static void cardsBySuits(ArrayList<Card> hand, EnumMap<Suit, Integer> count, EnumMap<Suit, ArrayList<Card>> list) {
    count.clear();
    list.clear();
    Iterator<Card> cards = hand.iterator();

    while (cards.hasNext()) {
      Card card = cards.next();
      Suit suit = card.getSuit();
      ArrayList<Card> values;
      Integer value;

      if (count.containsKey(suit)) {
        value = count.get(suit);
        values = list.get(suit);
      }
      else {
        value = new Integer(1);
        values = new ArrayList<Card>();
      }
      values.add(card);
      ++value;
      count.put(suit, value);
      list.put(suit, values);
    }
  }

  public static Rank hasStraight(ArrayList<Card> cards) {
    int consecutive, i;
    for (int a = cards.size() - 1; a >= (cards.size() - 5); a--) {
      consecutive = i = 1;
      Rank max = cards.get(a).getRank();
      int imax = max.ordinal();
      while (cards.contains(Rank.values()[imax - i++]))
        ++consecutive;
      if (consecutive == 4 && max.equals(Rank.Five)) {
        for (Iterator<Card> it = cards.iterator();it.hasNext();)
          if (it.next().getRank().equals(Rank.Ace)) {
            ++consecutive;
            break;
          }
      }
      if (consecutive == 5)
        return max;
    }
    return Rank.None;
  }

}
