// Julien Limoges (2011) LIMJ23049109
// julien.limoges.2 (at) ens.etsmtl.ca
package poker.cards;

import poker.ApplicationSupport;

public enum Suit {

  // toString() already defined through Enum
  // compareTo() already defined through Enum
  // equals() already defined through Enum

  // Values
  None    ("None"),
  Clubs   ("Suit.Clubs"),
  Diamonds("Suit.Diamonds"),
  Hearts  ("Suit.Hearts"),
  Spades  ("Suit.Spades");

  // Members
  private final String name;

  // Methods
  private Suit(String name) {
    this.name = ApplicationSupport.getResource(name);
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return name;
  }

}
