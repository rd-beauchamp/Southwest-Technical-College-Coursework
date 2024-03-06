/*
Author: Robert Beauchamp
Date: 11/17/2023

Write the following two generic methods using quick sort. 
The first method sorts the elements using the Comparable 
interface and the second uses the Comparator interface. 

public static <E extends Comparable<E>> void quickSort(E[] list)

public static <E> void quickSort(E[] list, Comparator<? super E> comparator)
*/
import java.util.Comparator;
import java.util.Random;

public class Main {
	

	
  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    quickSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),
                     new Circle(5), new Circle(6), new Circle(1), new Circle(2),
                     new Circle(3), new Circle(14), new Circle(12)};
    quickSort(list1, new GeometricObjectComparator());
    for (int i = 0; i < list1.length; i++) {
      System.out.println(list1[i] + " ");
    }
  }
  
  public static <E extends Comparable<E>> void quickSort(E[] list) {
	  //create index points
	  int highIndex = list.length -1;
	  int lowIndex = 0;
	  quickSort(list, lowIndex, highIndex);
  }
  
  public static <E extends Comparable<E>> void quickSort(E[] list, int lowIndex, int highIndex) {
	  if (lowIndex >= highIndex) {
	  return;
	  }
	  E pivot = list[highIndex];
	  //create pointers
	  int lPoint = lowIndex;
	  int rPoint = highIndex;
	 
	  //while loop to move pointers
	  while(lPoint < rPoint) {
		  
		  while(list[lPoint].compareTo(pivot) <= 0 && lPoint < rPoint) {
			  lPoint ++;
		  }
		  
		  while(list[rPoint].compareTo(pivot) >= 0 && lPoint < rPoint) {
			  rPoint--;
		  }
		  swap(list, lPoint, rPoint);
	  }
	  swap(list, lPoint, highIndex);
	  quickSort(list, lowIndex, lPoint - 1);
	  quickSort(list, lPoint + 1, highIndex);
  }
  
  public static <E> void quickSort(E[] list, int lowIndex, int highIndex, Comparator<? super E> comparator) {
	  if (lowIndex >= highIndex) {
		  return;
	  }
	  
	  //create pointers
	  int lPoint = lowIndex;
	  int rPoint = highIndex;
	  E pivot = list[highIndex];


	  while (lPoint < rPoint) {
		  
		  while(comparator.compare(list[lPoint], pivot) <= 0 && lPoint < rPoint) {
			  lPoint++;
		  }
		  
		  while(comparator.compare(list[rPoint], pivot) >= 0 && lPoint < rPoint) {
			  rPoint--;

		  } 
			  swap(list, lPoint, rPoint);
	  }
	  swap(list, lPoint, highIndex); 
	  quickSort(list, lowIndex, lPoint - 1, comparator);
	  quickSort(list, lPoint + 1, highIndex, comparator);

  }
  
  public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
	  //set high/low index
	  int lowIndex = 0;
	  int highIndex = list.length - 1;
	  
	  quickSort(list, lowIndex, highIndex, comparator);
  }
	  
  
  
  private static <E> void swap(E[] list, int i1, int i2) {
	  E temp = list[i1];
	  list[i1] = list[i2];
	  list[i2] = temp;
	  
  }
}
