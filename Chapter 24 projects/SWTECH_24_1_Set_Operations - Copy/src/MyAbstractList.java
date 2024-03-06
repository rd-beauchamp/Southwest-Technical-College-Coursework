import java.util.Arrays;

/*
* /** Adds the elements in otherList to this list.
* Returns true if this list changed as a result of the call 
* public boolean addAll(MyList<E> otherList); 
*
* /** Removes all the elements in otherList from this list
* Returns true if this list changed as a result of the call  
* public boolean removeAll(MyList<E> otherList); 
*
* /** Retains the elements in this list that are also in otherList 
* Returns true if this list changed as a result of the call  
* public boolean retainAll(MyList<E> otherList); 
*
*/

public abstract class MyAbstractList<E> implements MyList<E> {

	protected int size = 0; // The size of the list	
	
	/** Create a default list */
	protected MyAbstractList() {
	}

	public int comparator(int i, int j) {
		if(i > j) {
			return 1;
		}else if (i == j) {
			return 0;
		}else {
			return -1;
		}
	}
	public boolean addAll (MyList<E> otherList) {
		MyList<E> q = otherList;
		this.add(size, (E) otherList);
		
		if(comparator(this.size(), q.size()) == 0) {
			System.out.println("FakePing " + this.size() + " " + q.size());
			return false;
		}
		System.out.println("TruePing");
		return true;
		
	}
	
	public boolean removeAll(MyList<E> otherList) {
		MyList<E> q = otherList;
		this.clear();
		
		if(comparator(this.size(), q.size()) == 0) {
			System.out.println("FakePing " + this.size() + " " + q.size());
			return false;
		}
		System.out.println("TruePing");
		return true;
		

	}
	
	public boolean retainAll(MyList<E> otherList) {
		MyList<E> q = otherList;
		for(int i = 0; i < this.size();) {
			this.contains((E) otherList.get(i));
			if(otherList.contains((E) this.get(i)) == false) {	
				this.remove(this.get(i));
			}else {
				i++;
			}
		}
		if(comparator(this.size(), q.size()) == 0) {
			System.out.println("FakePing " + this.size() + " " + q.size());
			return false;
		}
		System.out.println("TruePing");
		return true;
		
	}
	/** Create a list from an array of objects */
	protected MyAbstractList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	/** Add a new element at the end of this list */
	public void add(E e) {
		add(size, e);
	}

	/** Return true if this list contains no elements */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Return the number of elements in this list */
	public int size() {
		return size;
	}

	/** Remove the first occurrence of the element o from this list.
	 *  Shift any subsequent elements to the left.
	 *  Return true if the element is removed. */
	public boolean remove(E e) {
		if (indexOf(e) >= 0) {
			remove(indexOf(e));
			return true;
		}
		else 
			return false;
	}
}