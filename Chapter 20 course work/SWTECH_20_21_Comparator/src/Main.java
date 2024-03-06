/*
* Author: Robert Beauchamp
* Date: 10/6/2023
*/
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

	public class Main {
	  public static void main(String[] args) {
	    GeometricObject[] list = {new Circle(5), new Rectangle(4, 5),
	        new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5), 
	        new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
	        new Circle(6.5), new Rectangle(4, 5)};
	    
	    	selectionSort(list, new GeometricObjectComparator());
	    	for(int i = 0; i < list.length; i ++) {
	    		System.out.println(list[i].getArea());
	    	}
	    	//below commented section is not relevant to assignment, commented out to preserve code.
	//    Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),
	//      new Circle(5), new Circle(6), new Circle(1), new Circle(2),
	//      new Circle(3), new Circle(14), new Circle(12)};
	//    selectionSort(list1, new GeometricObjectComparator());
	//    for (int i = 0; i < list1.length; i++)
	//      System.out.println(list1[i].getArea() + " ");
	  }
	
	private static <E> void selectionSort(E[] list, Comparator <? super E> comparator) {
		GeometricObjectComparator q = new GeometricObjectComparator();
		E tempI;
		E tempJ;	
		for(int i = 0; i < list.length; i ++) {
			for (int j = 0; j < list.length; j ++){
				tempI = list[i];
				tempJ = list[j];
				if(q.compare((GeometricObject)list[i], (GeometricObject)list[j]) > 0) {
					list[i] = tempJ;
					list[j] = tempI;
				}		
			}		
		}
	}
}
	