import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * (Pattern matching ) Write a program that prompts the user to enter two strings 
 * and tests whether the second string is a substring of the first string. 
 * Suppose the neighboring characters in the string are distinct.  
 * (Don’t use the indexOf method in the String class.) Analyze the time complexity 
 * of your algorithm. Your algorithm needs to be at least O(n) time.
 */


/*
 * time Analysis:
 * O(n^2)
 */
public class Main {

//	private static List<Character> l1 = new ArrayList<>();
//	private static List<Character> l2 = new ArrayList<>();
	
	public static String getBits() {
		Scanner input = new Scanner(System.in);
		System.out.print("Input String, the greater of two strings will be the main string: ");
		return input.nextLine();
	}
	
	//This method will compare both strings. It needs to find the earliest index
	
	public static int bitsCompare(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		int nearChar = -1;
		
		if (c1.length > c2.length) {
			for (int i = 0; i < c2.length; i++) {
				for (int j = 0; j < c1.length; j++) {
						if(c1[j] == c2[i]) {
						nearChar = j;
						return nearChar;
					}
				}
			}
		}else {
			for (int i = 0; i < c1.length; i++) {
				for (int j = 0; j < c2.length; j++) {
					if(c1[i] == c2[j]) {
					nearChar = j;
					return nearChar;
				}
				}
			}
		}
		return nearChar;
	}
	
	/*users shall henceforth be known as "Gits" gitPrompt will prompt the user for input
	 * For those wondering, I am just feeling a tad Orky of late.
	 */
	public static void gitPrompt() {
		String s1 = getBits();
		String s2 = getBits();
		int i = bitsCompare(s1,s2);
		
		if (i == -1) {
			System.out.println("No matches found!");
		}else {
			System.out.println("Nearest match found at index " + i);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gitPrompt();
	}

}
