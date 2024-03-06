import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * (Maximum consecutive increasingly ordered substring ) 
 * Write a program that prompts the user to enter a 
 * string and displays the maximum consecutive increasingly 
 * ordered substring. 
 * Analyze the time complexity of your program.
 * O(n)
 */
public class Main {

	private static List<Character> brokenBits = new ArrayList<>();
	private static Set<Character> stack = new LinkedHashSet<>();
	
	public static String getBits() {
		Scanner input = new Scanner(System.in);
		System.out.print("Input String: ");
		return input.nextLine();
	}
	
	public static void sortBits(String bits) {
		char[] b = bits.toCharArray();
		for (int i = 0; i < b.length; i++) {
			brokenBits.add(b[i]);
		}
		brokenBits.sort(Comparator.comparingInt(i -> 
		Collections.frequency(brokenBits, i)).reversed());
		stack.addAll(brokenBits);
		brokenBits.clear();
		brokenBits.addAll(stack);
		
		System.out.println(brokenBits);
	}

	public static void main(String[] args) {
		String bits = getBits();
		sortBits(bits);
	}

}
