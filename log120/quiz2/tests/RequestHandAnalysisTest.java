package tests;

import junit.framework.*;

import poker.RequestHandAnalysis;
import poker.hands.*;
import poker.cards.*;

public class RequestHandAnalysisTest extends TestCase {

  Hand sf;
  Hand f;
  Hand s;
  RequestHandAnalysis rsf;
  RequestHandAnalysis rf;
  RequestHandAnalysis rs;

  public void setUp() {
    sf = new Hand();
    f = new Hand();
    s = new Hand();
    
    // Creating the first hand, a straight flush
    sf.add(new Card(Suit.Spades, Rank.Ace));
    sf.add(new Card(Suit.Spades, Rank.Two));
    sf.add(new Card(Suit.Spades, Rank.Three));
    sf.add(new Card(Suit.Spades, Rank.Four));
    sf.add(new Card(Suit.Spades, Rank.Five));
    sf.add(new Card(Suit.Hearts, Rank.Three));
    sf.add(new Card(Suit.Clubs, Rank.Two));

    // Creating the second hand, a flush
    f.add(new Card(Suit.Clubs, Rank.Ace));
    f.add(new Card(Suit.Clubs, Rank.Jack));
    f.add(new Card(Suit.Clubs, Rank.Ten));
    f.add(new Card(Suit.Clubs, Rank.Seven));
    f.add(new Card(Suit.Clubs, Rank.Five));
    f.add(new Card(Suit.Clubs, Rank.Three));

    // Creating the third hand, a straight
    s.add(new Card(Suit.Clubs, Rank.Ace));   
    s.add(new Card(Suit.Hearts, Rank.King));   
    s.add(new Card(Suit.Spades, Rank.Jack)); 
    s.add(new Card(Suit.Diamonds, Rank.Queen));  
    s.add(new Card(Suit.Spades, Rank.Ten));  
    s.add(new Card(Suit.Hearts, Rank.Three)); 
    s.add(new Card(Suit.Clubs, Rank.Two));    

    rsf = new RequestHandAnalysis(sf);
    rf = new RequestHandAnalysis(f);
    rs = new RequestHandAnalysis(s);
  }

  public void tearDown() {
    sf = f = s = null;
    rsf = rf = rs = null;
  }

  public void testisFlush() {
    assertEquals(rsf.isFlush(), Suit.Spades);
    assertEquals(rf.isFlush(), Suit.Clubs);
    assertEquals(rs.isFlush(), Suit.None);
  }

  public void testisStraight() {
    assertEquals(rsf.isStraight(), true);
    assertEquals(rf.isStraight(), false);
    assertEquals(rs.isStraight(), true);
  }

}
