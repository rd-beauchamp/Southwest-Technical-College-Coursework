
	
	import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
	
	public class Main {
		
	  public static String getInput(Scanner input) {
		  System.out.println("Please input a State (emotional states do not count) : ");
		  String userState = input.nextLine();
		  return userState;
	  }
		
	  public static void main(String[] args) {
		  Scanner input = new Scanner(System.in);
		  String[][] stateCapitol = {
					 {"Alabama", "Montgomery"},				 	
					 {"Alaska", "Juneau"},				    
					 {"Arizona", "Phoenix"},				  
					 {"Arkansas", "Little Rock"},				  
					 {"California", "Sacramento"},			   
					 {"Colorado", "Denver"},			 
					 {"Connecticut", "Hartford"},
					 {"Delaware", "Dover"},
					 {"Florida", "Tallahassee"},
					 {"Georgia", "Atlanta"},
					 {"Hawaii", "Honolulu"},
					 {"Idaho", "Boise"},
					 {"Illinois", "Springfield"},
					 {"Indiana", "Indianapolis"},
					 {"Iowa", "Des Moines"},
					 {"Kansas", "Topeka"},
					 {"Kentucky", "Frankfort"},
					 {"Louisiana", "Baton Rouge"},
					 {"Maine", "Augusta"},
					 {"Maryland", "Annapolis"},
					 {"Massachusettes", "Boston"},
					 {"Michigan", "Lansing"},
					 {"Minnesota", "Saint Paul"},
					 {"Mississippi", "Jackson"},
					 {"Missouri", "Jefferson City"},
					 {"Montana", "Helena"},
					 {"Nebraska", "Lincoln"},
					 {"Nevada", "Carson City"},
					 {"New Hampshire", "Concord"},
					 {"New Jersey", "Trenton"},
					 {"New York", "Albany"},
					 {"New Mexico", "Santa Fe"},
					 {"North Carolina", "Raleigh"},
					 {"North Dakota", "Bismarck"},
					 {"Ohio", "Columbus"},
					 {"Oklahoma", "Oklahoma City"},
					 {"Oregon", "Salem"},
					 {"Pennsylvania", "Harrisburg"},
					 {"Rhode Island", "Providence"},
					 {"South Carolina", "Columbia"},
					 {"South Dakota", "Pierre"},
					 {"Tennessee", "Nashville"},
					 {"Texas", "Austin"},
					 {"Utah", "Salt Lake City"},
					 {"Vermont", "Montpelier"},
					 {"Virginia", "Richmond"},
					 {"Washington", "Olympia"},
					 {"West Virginia", "Charleston"},
					 {"Wisconsin", "Madison"},
					 {"Wyoming", "Cheyenne"}
		  };
			
		  final Map<String, String> map = new HashMap<>(stateCapitol.length);
		  for (String[] mapping : stateCapitol) {
			  map.put(mapping[0], mapping[1]);
		  }
		  System.out.println("debugMe " + map);	  
		  
		  for(int i = 0; i < 1; i += 0) { 
			  String keyCompare = getInput(input);
			  
			  if (map.containsKey(keyCompare)) {
				  System.out.println(map.get(keyCompare));
			  }else {
				  System.out.println("Invalid entry, try again.");
			  }
		  }
	  }
	}

