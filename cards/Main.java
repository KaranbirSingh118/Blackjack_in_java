/*
 * Karanbir Singh
 * Student ID - 991550003
 * SYSt10199 - Web Programming
 */
package cards;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application {
 int currentCard = 0 , sum=0;
 ArrayList<Card> deck = new ArrayList();
 ArrayList<Card> count = new ArrayList();
 ArrayList<Card> count2 = new ArrayList();
 BorderPane root = new BorderPane();
   FlowPane buttonPanel = new FlowPane();
   FlowPane playerPanel = new FlowPane();
   FlowPane dealerPanel = new FlowPane();
   
   Label l1 = new Label("0");
   Label l2 = new Label("0");
   Button btn2 = new Button("Stand");
   Button btn1 = new Button("Hit");
   Button btn3 = new Button("restart");
   
 @Override
 public void start(Stage primaryStage) {
  try {
   
   primaryStage.setTitle("Blackjack");
    
   buttonPanel.setAlignment(Pos.CENTER);
   playerPanel.setAlignment(Pos.CENTER);
   dealerPanel.setAlignment(Pos.CENTER);
   
   root.setBottom(buttonPanel);
   root.setCenter(playerPanel);
   root.setTop(dealerPanel);
   
 
   buttonPanel.getChildren().add(btn1);
 
   buttonPanel.getChildren().add(btn2);
   
   buttonPanel.getChildren().add(btn3);
   
   buttonPanel.getChildren().add(l1);
   buttonPanel.getChildren().add(l2);
   
   FlowPane.setMargin(l1,new Insets(10,10,10,10));
   FlowPane.setMargin(l2,new Insets(50,50,50,50));
   initial();
   btn1.setOnAction((ActionEvent event) -> {
       
         
       dealToPlayer();
       
       System.out.println("Hitting");
       
       l1.setText("" +sum(count));
           
       l1.setText(checkScore(sum(count),sum(count2)));
       
       if(l1.getText() == "You won" || l1.getText() == "You lost")
       {
           showDealerHand();
       }
   });
   
     btn2.setOnAction((ActionEvent event) -> {
       
      showDealerHand();
       l1.setText(checkWinner(sum(count),sum(count2)));
       System.out.println("Standing");
       
   });
     
     btn3.setOnAction((ActionEvent event) -> {
       
         remove();
         initial();
       
   });
   
   Scene scene = new Scene(root,800,500);
   scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   primaryStage.setScene(scene);
   primaryStage.show();
   
   
  } catch(Exception e) {
   e.printStackTrace();
  }
 }
 
 public void showDealerHand()
 {
     while(sum(count2)<=16)
       {
           dealToDealer();
           l2.setText("" + sum(count2));
       }
     dealerPanel.getChildren().remove(0);
       btn1.setDisable(true);
       btn2.setDisable(true);
 }
 public void initial()
 {
     deck.clear();
     addDeck();
     dealToPlayer();
   dealToPlayer();
   hide();
   dealToDealer();
   l1.setText("" +sum(count));
   l2.setText("" +sum(count2));
   l1.setText(checkScore(sum(count),sum(count2)));
 }
 
 public void hide()
 {
     Image img = new Image("cards1/purple_back.png");
     ImageView imgView = new ImageView(img);
     imgView.setFitWidth(150);
     imgView.setPreserveRatio(true);
     dealerPanel.getChildren().add(imgView);
 }
 
 public void dealToDealer()
 {
       currentCard();
       Card c1 = deck.remove(currentCard);
       
       count2.add(c1);
       Image img = new Image("cards1/" + c1.getImageName() + ".png");
       ImageView imgView = new ImageView(img);
       imgView.setFitWidth(150);
       imgView.setPreserveRatio(true);
       dealerPanel.getChildren().add(imgView);
       System.out.println(c1.getFullName());
 }
 
 public int sum(ArrayList<Card> count)
 {
     sum=0;
     for (Card num : count) {	      
           sum+=num.getRank();
      }
     return sum;
 }
 
 public void currentCard()
 {
     currentCard = (int)(Math.random() * deck.size());
 }
 public String checkScore(int player, int dealer)
 {
     if(player > 21 || dealer == 21)
       {
           btn1.setDisable(true);
           return "You lost";
       }
       if(player == 21 || dealer > 21)
       {
           btn1.setDisable(true);
           return "You won";
       }
       return l1.getText();
 }
 public String checkWinner(int player, int dealer)
 {
     String winner = "";
       if(player > dealer && (dealer <= 21 && dealer > 16))
       {
           winner= "You won"; 
       }
       if(dealer>player  && (dealer <= 21 && dealer > 16))
       {
           winner= "You lost";
       }
       if(winner == "")
       winner = checkScore(player,dealer);
       return winner;
 }
 
 public void dealToPlayer()
 {
     currentCard();
     Card c = deck.remove(currentCard);
       
       count.add(c);
       Image img = new Image("cards1/" + c.getImageName() + ".png");
       ImageView imgView = new ImageView(img);
       imgView.setFitWidth(150);
       imgView.setPreserveRatio(true);
       playerPanel.getChildren().add(imgView);
       System.out.println(c.getFullName());
 }
 
 public void remove()
 {
        count.clear();
        playerPanel.getChildren().clear();
        l1.setText("0");
        count2.clear();
        dealerPanel.getChildren().clear();
        l2.setText("0");
        btn1.setDisable(false);
        btn2.setDisable(false);
 }
 
 public void addDeck()
 {
     for (int i = 0; i < 13; i++) {
    Card c = new Card("Hearts", i + 1);
    deck.add(c);
   }
   for (int i = 0; i < 13; i++) {
    Card c = new Card("Diamonds", i + 1);
    deck.add(c);
   }
   for (int i = 0; i < 13; i++) {
    Card c = new Card("Spades", i + 1);
    deck.add(c);
   }
   for (int i = 0; i < 13; i++) {
    Card c = new Card("Clubs", i + 1);
    deck.add(c);
   }
 }
 
 public static void main(String[] args) {
  launch(args);
 }
}
