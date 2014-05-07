package Sort;

import java.util.Scanner;

public class SelectionSort {

	public static void main(String[] args) {

		SelectionSort s = new SelectionSort();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the length of array");
		int len = scan.nextInt();
		
		int[] a = new int[len];
		
		System.out.println("Enter the value in arraylist");
		for(int i = 0; i < len; i++){
			a[i] = scan.nextInt();
		}
		
		System.out.println("Values are -");
		for(int i = 0; i < len; i++){
			System.out.println(a[i]);
		}

		
		// Logic for Selection Sort start here
		
		for(int i = 0; i < len; i++){
			int temp;
			int loc = s.findmin(a, i, len);
			
			if(i != loc){
				temp = a[i];
				a[i] = a[loc];
				a[loc] = temp;
						
			}
		}

		// Logic for Selection Sort end here
		

		System.out.println("Values after sorting are -");
		for(int i = 0; i < len; i++){
			System.out.println(a[i]);
		}

		
	}

	public int findmin(int[] a, int i, int len){
		int loc = i;
		
		int min = a[i];
		for(int j = i+1; j < len; j++){
			if(min > a[j]){
				loc = j;
				min = a[j];
			}
		}
		
		return loc;
	}
	
}
