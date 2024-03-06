/*
 * Create a class named WordOccurrence that implements the Comparable interface. 
 * The class contains two fields, word and count. 
 * use the compareTo method compares the counts. 
 * 
 * For each pair in the hash set, create an instance of 
 * WordOccurrence store it in an array list. 
 * 
 * Sort the array list using the Collections.sort method. 
 */



import java.util.*;
import java.util.Map.Entry;

public class Main extends WordOccurence{
	public static void main(String[] args) {
		// Set text in a string
		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";

		Map<String, Integer> map = new HashMap<>();

		String[] words = text.split("[\\s+\\p{P}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
						
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
					
					}else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort((entry1, entry2) ->
		entry1.getValue().compareTo(entry2.getValue()) == 0 ? entry1.getKey().compareTo(entry2.getKey()) : entry1.getValue().compareTo(entry2.getValue())
		);
		
		map.clear();
		
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			map.put(entry.getKey(), entry.getValue());

		}

	}
}

class WordOccurence implements Comparable<WordOccurence>{

	private String word = "";
	private String keyWord = "";
	private int count = 0;
	private int hiCount = 0;
	protected ArrayList<String> sortingHat = new ArrayList<>();
	
	public WordOccurence() {
		
	}
	
	public String setWord(String word) {
		return this.word = word;
	}

	public int setCount(int count) {
		return this.count = count;
	}
	
	public void setHat(String word) {
		sortingHat.add(word);

	}
	
	@Override
	public int compareTo(WordOccurence o) {
		
		if (this.count > o.count) {
			return 1;
		}else if( this.count < o.count) {
			return -1;
		}else {
			return 0;
		}
	}
	
}