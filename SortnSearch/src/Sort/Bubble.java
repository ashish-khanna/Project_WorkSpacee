package Sort;
import java.util.Scanner;


public class Bubble {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Specify the length of array");
		final int length = scan.nextInt();
		
		System.out.println("Enter the unsorted list");
		
		
		int[] a = new int[length];
		
		for(int i=0; i<length; i++){
			a[i]= scan.nextInt();
		}
		
		System.out.println("Number in a array are - ");
		for(int j=0; j<length; j++){
			System.out.println("Number is - " + a[j]);
		}
		
		//Bubble sort logic start here
		
		int flag=1;
		
		for(int i=0; i<(length - 1)&&flag==1; i++){
			flag=0;
			for(int j=0; j<(length-1-i); j++){
				if(a[j] > a[j+1]){
					a[j] = a[j] + a[j+1];
					a[j+1] = a[j] - a[j+1];
					a[j] = a[j] - a[j+1];
					flag=1;
				}
			}
		}
		
		//Bubble sort logic end here
		
		System.out.println("Number in a array after sorting are - ");
		for(int j=0; j<length; j++){
			System.out.println("Number is - " + a[j]);
		}
		
		
	}

}
