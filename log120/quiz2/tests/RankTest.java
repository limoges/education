package tests;

import junit.framework.*;

import poker.cards.Rank;

public class RankTest extends TestCase {

  public void setUp() {
  }

  public void tearDown() {
  }

  public void testSymbol() {
    assertEquals('Z', Rank.Joker.getSymbol());
    assertEquals('2', Rank.Two.getSymbol());
    assertEquals('K', Rank.King.getSymbol());
  }
}
