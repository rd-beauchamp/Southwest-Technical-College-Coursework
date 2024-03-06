import java.util.Scanner;
public class Main extends GenericQueue{
	
	public static <E> void main (String[] args) {
		GenericQueue<String> q = new GenericQueue<String>();
		q.enqueue("pie");
		q.enqueue("backwars is");
		q.enqueue("eip");
		q.toString();
		
		q.deque();
		q.toString();
	}
	
}
