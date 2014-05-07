package Sort;

import java.util.Scanner;

public class InsertionSort {

	public static void main(String[] args) {

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

		// Logic for Insertion Sort start here
		
		for(int j = 1; j <= (len - 1); j++){
			int temp = a[j];
			int ptr = j;
			int k = ptr-1;
			while((k != -1)&&(a[k] > temp)){
				a[k+1] = a[k];
				k--;
			}
			a[k+1] = temp;
		}
		
		// Logic for Insertion Sort ends here
		
		System.out.println("Values after sorting are -");
		for(int i = 0; i < len; i++){
			System.out.println(a[i]);
		}
		
	}

}
