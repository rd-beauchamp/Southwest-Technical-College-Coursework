
import java.util.Scanner;

public class DeckController{
	
	DeckController(){
		
	}
	
	public DeckBuilder fateDeckConstruction() {
		DeckBuilder BuiltFateDeck = new DeckBuilder();
		BuiltFateDeck.getTwistDeck();	
		BuiltFateDeck.deckShuffle();
		BuiltFateDeck.drawSingleCard();
		return BuiltFateDeck;
	
	}	
}


