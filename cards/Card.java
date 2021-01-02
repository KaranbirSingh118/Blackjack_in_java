/*
 * Karanbir Singh
 * Student ID - 991550003
 * SYSt10199 - Web Programming
 */
package cards;

public class Card {
 String suit;
 int rank; //1 means Ace, .. 11 means J, 12 means Q, 13 means K 
 
 public Card(String suit, int rank){
  this.suit = suit; 
  this.rank = rank;
 }
 
 public int getRank()
 {
     return this.rank;
 }
 public String getFullName(){
    if (rank == 1){
   return "Ace of " + suit;
  }
   if (rank == 11){
   return "Jack of " + suit;
  }
  if (rank == 12){
   return "Queen of " + suit;
  }
  if (rank == 13)
   return "King of " + suit;
  
  return rank + suit;
 }
 
 public String getImageName(){
    if (rank == 1){
   return "A" + this.suit.charAt(0);
  }
   if (rank == 11){
   return "J" + this.suit.charAt(0);
  }
  if (rank == 12){
   return "Q" + this.suit.charAt(0);
  }
  if (rank == 13)
   return "K" + this.suit.charAt(0);
  
  return "" + this.rank + this.suit.charAt(0);
 }
 
 
 public int getBlackjackValue(){
  if (rank > 10){
   return 10;
  }
  return rank;
 }
}
