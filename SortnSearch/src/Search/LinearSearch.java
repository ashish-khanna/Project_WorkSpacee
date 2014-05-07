package Search;

import java.util.Scanner;

public class LinearSearch {

	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the length of array");
		int len = scan.nextInt();
		
		int[] a = new int[(len+1)];
		
		System.out.println("Enter all the element of arraylist");
		
		for(int i=0; i<len; i++){
			a[i]=scan.nextInt();
		}
		
		System.out.println("Enter the element you are searching in array");
		int item = scan.nextInt();
		
		a[len] = item;
		
		int j = 0;
		while(a[j] != item){
			j = j+1;
		}
		if(j == len){
			System.out.println("Unsuccessful");
		}else{
			System.out.println("Element is placed in index -> " + j + " location ->" + (j+1));
		}
	}

}
