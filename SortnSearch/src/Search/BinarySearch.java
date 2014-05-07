package Search;

import java.util.Scanner;

public class BinarySearch {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the length of array");
		int len = scan.nextInt();
		
		int[] a = new int[len];
		
		System.out.println("Enter all the values in arraylist");
		
		for(int i = 0; i < len; i++){
			a[i] = scan.nextInt();
		}
		
		System.out.println("Enter all the values in arraylist");
		int item = scan.nextInt();
		
		// Logic for Binary search start here
		
		int begin = 0;
		int end = (len-1);
		
		int mid = (int)((begin + end)/2);
		
		while((begin <= end) && (a[mid] != item)){
			if(a[mid] > item){
				end = mid - 1;
			}else if(a[mid] < item){
				begin = mid + 1;
			}
			mid = (int)((begin + end)/2);
		}
		
		if(begin > end){
			System.out.println("Item could be found");
		}else{
			System.out.println("Item is located at position -> " + (mid + 1));
		}
			
		

	}

}
