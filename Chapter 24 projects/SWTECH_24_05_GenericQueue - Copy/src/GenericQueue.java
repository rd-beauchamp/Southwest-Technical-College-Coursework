import java.util.LinkedList;

public class GenericQueue<E> extends LinkedList {
	
//	private java.util.LinkedList<E> list 
//		= new java.util.LinkedList<>();
	
	public void enqueue (E e) {
		
		this.addLast(e);
	}
	
	public Object deque() {
		
		return removeFirst();	
	}
	
	public int getSize() {
		
		return size();
	}
	
	
	@Override
	public String toString() {
		
		return "Queue: " + toString();
	}
	
	public void testRun() {
		enqueue((E) "pie");
		enqueue((E) "backwars is");
		enqueue((E) "eip");
		toString();
		
		deque();
		toString();
		
	}
}
