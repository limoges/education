// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.cards;

import poker.ApplicationSupport;

public enum Rank {

  // toString() already defined through Enum
  // compareTo() already defined through Enum
  // equals() already defined through Enum

  // Values
  None  ('N', "None"),
  Joker ('Z', "Rank.Joker"),  
  Two   ('2', "Rank.Two"),      
  Three ('3', "Rank.Three"),
  Four  ('4', "Rank.Four"),    
  Five  ('5', "Rank.Five"),    
  Six   ('6', "Rank.Six"),      
  Seven ('7', "Rank.Seven"),  
  Eight ('8', "Rank.Eight"),  
  Nine  ('9', "Rank.Nine"),    
  Ten   ('X', "Rank.Ten"),     
  Jack  ('J', "Rank.Jack"),   
  Queen ('Q', "Rank.Queen"), 
  King  ('K', "Rank.King"),   
  Ace   ('A', "Rank.Ace");

  // Members
  private final char symbol;
  private final String name;

  // Methods
  private Rank(char symbol, String name) {
    this.symbol = symbol;
    this.name = ApplicationSupport.getResource(name);
    System.out.println("Rank");
  }

  public String getName() {
    return name;
  }

  public char getSymbol() {
    return symbol;
  }

  public String toString() {
    return name;
  }

}
