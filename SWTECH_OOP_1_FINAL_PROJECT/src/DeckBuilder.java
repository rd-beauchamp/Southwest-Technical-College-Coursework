
import java.util.Scanner;

public class DeckBuilder {

	public int[] fateDeck = new int[54];
	private String [] twistDeck = new String [13];
	public String[] suits = {"Rams", "Tomes", "Masks", "Crows"};
	public String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9"
			, "10", "11", "12", "13"};
	
	public int z;
	public int temp;
	public int tempIndex;
	public boolean badLuck = true;
	
	DeckBuilder(){
		
	}
	
	public void getFateDeck() {
		for(z = 0; z < fateDeck.length; z++) {		
			fateDeck[z] = z;
		}
	}
	public void deckShuffle() {
		getFateDeck();
		for(int i = 0; i < fateDeck.length; i++) {
	
		tempIndex = (int)(Math.random() * fateDeck.length);
		temp = fateDeck[i];
		fateDeck[i] = fateDeck[tempIndex];
		fateDeck[tempIndex] = temp;
		}
	}
	
	public void drawSingleCard() {
		Scanner input = new Scanner(System.in);
		int i = 0;
		double j = 54;
		
		System.out.println("please press return/enter to draw a card!");
		
		while(i < fateDeck.length) {
			String toggle = input.nextLine();
			double odds = ((1 / j) * 100);
			if (fateDeck[i] == 53) {
				System.out.println("You drew the Black Joker, Critical Failure!\n");
				this.badLuck = false;
				i++;
			}else if(fateDeck[i] == 52) {
				System.out.println("You drew the Red Joker! Critical Success!\n");
				i++;
			}else {
				String suit = suits[fateDeck[i] / 13];
				String cardRank = ranks[fateDeck[i] % 13];
				
				System.out.println("You drew a " + cardRank + " of " + suit + "!");
				
				if(this.badLuck == true) {
					System.out.println(odds +  "% chance of drawing a critical failure.\n");
				}else {
					System.out.println("0% chance of critical failure!\n");
				
				}
				j -= 1;
				i++;
			}
		}
		System.out.println("Deck is empty!");
			
	}
	
	public void getTwistDeck() {
		Scanner input = new Scanner(System.in);
		int i = 0;
		System.out.println("Please build your twist deck.");
		System.out.println("1 for your defining suit. (1, 5, 9, 13)");
		System.out.println("2 for your ascendant suit. (4, 8, 12)");
		System.out.println("3 for your center suit. (3, 7, 11)");
		System.out.println("4 for your descendant suit. (2, 6, 10)");
		
			while (i < 4) {
			System.out.println("Please elect the strength of your "+ suits[i] + " suit.");
			int uPick = input.nextInt();
			int[] storePick = new int [4];
			storePick[i] = uPick;
			
//				if (storePick[i] == storePick[1] || storePick[i] == storePick[2] || storePick[i] == storePick[3]) {
//					System.out.println("No duplicates allowed! try again!");
//					twistBuild();
//				}
			
			switch(uPick) {
			
			case 1: 
				twistDeck[0] = "1 of " + suits[i];
				twistDeck[4] = "5 of " + suits[i];
				twistDeck[8] = "9 of " + suits[i];
				twistDeck[12] = "King of " + suits[i];
				i++;
				break;
			
			case 2:
				twistDeck[3] = "4 of " + suits[i];
				twistDeck[7] = "8 of " + suits[i];
				twistDeck[11] = "Queen of " + suits[i];
				i++;
				break;
				
			case 3:
				twistDeck[2] = "3 of " + suits[i];
				twistDeck[6] = "7 of " + suits[i];
				twistDeck[10] = "Jack of " + suits[i];
				i++;
				break;
				
			case 4:
				twistDeck[1] = "2 of " + suits[i];
				twistDeck[5] = "6 of " + suits[i];
				twistDeck[9] = "10 of " + suits[i];
				i++;
				break;
			}
		}
			System.out.println("your twist deck is:");
			for (int q = 0; q < twistDeck.length; q++) {
				System.out.println(twistDeck[q]);
			}
	}
	
}

