package Assig4;

import java.util.Scanner;

import Assig4.Card.Ranks;
import Assig4.Card.Suits;

public class War {
	public static Scanner input = new Scanner(System.in);
	int round_req;
	int round;
	MultiDS<Card> deck = new MultiDS<Card>(10);
	MultiDS<Card> hand0 = new MultiDS<Card>(15);
	MultiDS<Card> hand1 = new MultiDS<Card>(15);
	MultiDS<Card> discard0 = new MultiDS<Card>(15);
	MultiDS<Card> discard1 = new MultiDS<Card>(15);
	MultiDS<Card> inplay = new MultiDS<Card>(15);
	
	public static void main(String[] args) {
		War w = new War();
		System.out.print("java War ");
		w.round_req = input.nextInt(); 
		
		System.out.println("Welcome to the Game of War!\r\n" + 
				"\r\n" + 
				"Now dealing the cards to the players...");
		w.play();

	}

	private void play() {
	
		for (Suits s : Suits.values()) {
		  for (Ranks r : Ranks.values()) {
		    Card card = new Card(s,r);
		    deck.addItem(card);
		  }
		}
		deck.shuffle();
  
		for (int i = 0; i < deck.size(); i++)
		{
			Card bogus = deck.removeItem();
			hand0.addItem(bogus);
			deck.shiftLeft();
		}
		System.out.println("Here is Player 0's Hand:\r\n"+hand0.toString()+"\r\n");

		for (int i = 0; i < deck.size(); i++)
		{
			Card bogus = deck.removeItem();
			hand1.addItem(bogus);
			deck.size++;
		}
		System.out.println("Here is Player 1's Hand:\r\n"+hand1.toString()+"\r\n");
		deck.clear();
		
		System.out.println("Starting the WAR!"+"\r\n");
		startWar();

	}

	private void startWar() {
		while((round<round_req)&&check(hand0,discard0)&&check(hand1,discard1)) {
			Card com0 = hand0.removeItem();
			Card com1 = hand1.removeItem();
			inplay.addItem(com0);
			inplay.addItem(com1);
			compareCards(com0, com1, round);
			round++;
		}
		
		if (!check(hand0,discard0)) {
			System.out.println("	Getting and shuffling the pile for player 0");
			System.out.println();
			System.out.println("Player 0 is out of cards!\r\n" + 
					"Player 1 is the WINNER!");
		}
		if (!check(hand1,discard1)) {
			System.out.println("	Getting and shuffling the pile for player 1");
			System.out.println();
			System.out.println("Player 1 is out of cards!\r\n" + 
					"Player 0 is the WINNER!");
		}
		if ((round == round_req)) {
			System.out.println();
			System.out.println("After "+round+" rounds here is the status:\r\n" + 
					"	Player 0 has "+(hand0.size+discard0.size)+" cards\r\n" + 
					"	Player 1 has "+(hand1.size+discard1.size)+" cards");
			if ((hand0.size+discard0.size)>(hand1.size+discard1.size)) {
				System.out.println("Player 0 is the WINNER!");
			}
			else if ((hand0.size+discard0.size)<(hand1.size+discard1.size)) {
				System.out.println("Player 1 is the WINNER!");
			}
			else {
				System.out.println("It is a STALEMATE!");
			}
		}

	}
	
	public void compareCards(Card x, Card y, int round)
	{
		int result = x.compareTo(y);
		if (result > 0) {
			System.out.println("Player 0 Wins Rnd "+round+": "+x + " beats " + y + " : "+inplay.size()+" cards");
			for (int i = 0; i < inplay.size(); i++)
			{
				Card bogus = inplay.removeItem();
				discard0.addItem(bogus);
				inplay.size++;
			}
			inplay.clear();
		}	
		
		else if (result < 0) {
			System.out.println("Player 1 Wins Rnd "+round+": "+x + " loses to " + y + " : "+inplay.size()+" cards");

			for (int i = 0; i < inplay.size(); i++)
			{
				Card bogus = inplay.removeItem();
				discard1.addItem(bogus);
				inplay.size++;
			}
			inplay.clear();
		}
				
		else {
			System.out.println("	WAR: "+x + " ties " + y);
			war();
			round++;
		}	
	}

	private void war() {
		if (check(hand0,discard0)&&check(hand1,discard1)) {
		Card com2 = hand0.removeItem();
		Card com3 = hand1.removeItem();
		inplay.addItem(com2);
		inplay.addItem(com3);
		System.out.println("	Player 0:"+com2+" and Player 1:"+com3+" are at risk!");
		}
		if (check(hand0,discard0)&&check(hand1,discard1)) {
		Card com4 = hand0.removeItem();
		Card com5 = hand1.removeItem();
		inplay.addItem(com4);
		inplay.addItem(com5);
		compareCards(com4, com5, round);
		}
		
	}
	
	public boolean check(MultiDS<Card> hand, MultiDS<Card> discard) {
		if (hand.empty()&&discard.empty()) {
			return false;
		}
		else if(hand.empty()) {
			int m = 0;
			if (hand == hand1) {
				m = 1;
			}
			System.out.println("	Getting and shuffling the pile for player "+m);
			discard.shuffle();
			
			for (int i = 0; i < discard.size(); i++)
			{
				Card bogus = discard.removeItem();
				hand.addItem(bogus);
				discard.size++;
			}
			discard.clear();
			return true;
		}
		else
			return true;
	}
}
