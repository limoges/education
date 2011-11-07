
import java.util.Vector;

public class Hand implements Comparable {

  Suit suit;
  Rank rank;
  PokerRank poker;
  Vector<Card> cards;

  Hand() {
  }

  void add() {
  }

  //TODO: check how we order different objects
  int compareTo(Object o) {
    if (!(o instanceof Hand))
      return -1;

    Hand h = (Hand) o;
    return this.poker.compareTo(h.poker);
  }

  int compareTo(Hand h) {
    return this.poker.compareTo(h.poker);
  }


  boolean isValid() {
    // ?
  }

}
