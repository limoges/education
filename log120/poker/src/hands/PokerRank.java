// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.hands;

import poker.ApplicationSupport;

public enum PokerRank {

  // Values
  HighCard          ("PokerRank.HighCard"),
  Pair              ("PokerRank.Pair"),
  TwoPair           ("PokerRank.TwoPair"),
  ThreeOfAKind      ("PokerRank.ThreeOfAKind"),
  Straight          ("PokerRank.Straight"),
  Flush             ("PokerRank.Flush"),
  FullHouse         ("PokerRank.FullHouse"),
  FourOfAKind       ("PokerRank.FourOfAKind"),
  StraightFlush     ("PokerRank.StraightFlush"),
  FiveOfAKind       ("PokerRank.FiveOfAKind"),
  RoyalStraightFlush("PokerRank.RoyalStraightFlush");

  // Members
  private final String name;

  // Methods
  private PokerRank(String name) {
    this.name = ApplicationSupport.getResource(name);
  }

}
