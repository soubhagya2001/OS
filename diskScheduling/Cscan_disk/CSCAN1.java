package diskScheduling.Cscan_disk;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class CSCAN1 {
	static int seek_count = 0;
	static int distance = 0;
	static int cur_track = 0;
	static int disk_size = 200;
	static Vector<Integer> left = new Vector<Integer>();
	static Vector<Integer> right = new Vector<Integer>();
	static Vector<Integer> seek_sequence
		= new Vector<Integer>();
	
	public static void Right(Vector<Integer> right , Vector<Integer> seek_sequence , int head) {
		for (int i = 0; i < right.size(); i++) {
			cur_track = right.get(i);

			// Appending current track to seek sequence
			seek_sequence.add(cur_track);

			// Calculate absolute distance
			distance = Math.abs(cur_track - head);

			// Increase the total count
			seek_count += distance;

			// Accessed track is now new head
			head = cur_track;
			System.out.println(cur_track + "  \t\t  " + distance);
			
			
			//head = 0;
			
			
		}
	}
	
	public static void Left(Vector<Integer> right , Vector<Integer> seek_sequence , int head) {
		for (int i = 0; i < left.size(); i++) {
//			if(left.get(i) == 0) {
//				continue;
//			}
//			
			cur_track = left.get(i);

			// Appending current track to
			// seek sequence
			seek_sequence.add(cur_track);

			// Calculate absolute distance
			distance = Math.abs(cur_track - head);

			// Increase the total count
			seek_count += distance;

			// Accessed track is now the new head
			head = cur_track;
			System.out.println(cur_track + "  \t\t  " + distance);
		}
		
	}

	public static void CSCAN(int arr[], int head , String direction)
	{
		

		

		// Appending end values which has
		// to be visited before reversing
		// the direction
		left.add(0);
		right.add(disk_size);

		// Tracks on the left of the
		// head will be serviced when
		// once the head comes back
		// to the beginning (left end).
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < head)
				left.add(arr[i]);
			if (arr[i] >= head)
				right.add(arr[i]);
		}

		// Sorting left and right vectors
		Collections.sort(left);
		Collections.sort(right);

		System.out.println("\n Position\t Seek time");
		// First service the requests
		// on the right side of the
		// head.
		
		if(direction.equals("left")) {
			
			Left(left , seek_sequence, head);
			head = disk_size;
			seek_count += (disk_size - 1);
			System.out.println(head+"  \t\t  " + "0");
			Right(left , seek_sequence, head);
			
		}else if(direction.equals("right")){
			
			Right(left , seek_sequence, head);
			head = 0;
			seek_count += (disk_size - 1);
			System.out.println(head+"  \t\t  " + (disk_size));
			Left(left , seek_sequence , head);
			
		}else {
			System.out.println("Invalid direction...");
		}
		
		
//		for (int i = 0; i < right.size(); i++) {
//			cur_track = right.get(i);
//
//			// Appending current track to seek sequence
//			seek_sequence.add(cur_track);
//
//			// Calculate absolute distance
//			distance = Math.abs(cur_track - head);
//
//			// Increase the total count
//			seek_count += distance;
//
//			// Accessed track is now new head
//			head = cur_track;
//			System.out.println(cur_track + "  \t\t  " + distance);
//		}
//		
//		
//		
//
//		// Once reached the right end
//		// jump to the beginning.
//		head = 0;
//
//		// adding seek count for head returning from 199 to
//		// 0
//		seek_count += (disk_size - 1);
//		System.out.println(head+"  \t\t  " + (disk_size-1));
//		// Now service the requests again
//		// which are left.
//		for (int i = 0; i < left.size(); i++) {
//			if(left.get(i) == 0) {
//				continue;
//			}
//			
//			cur_track = left.get(i);
//
//			// Appending current track to
//			// seek sequence
//			seek_sequence.add(cur_track);
//
//			// Calculate absolute distance
//			distance = Math.abs(cur_track - head);
//
//			// Increase the total count
//			seek_count += distance;
//
//			// Accessed track is now the new head
//			head = cur_track;
//			System.out.println(cur_track + "  \t\t  " + distance);
//		}

		System.out.println("Total Seek Time :  " + seek_count);

		System.out.println("Throughput : " + (float)arr.length/seek_count);
	}

	// Driver code
	public static void main(String[] args) throws Exception
	{

	
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the initial head position: ");
        int HeadPosition = scanner.nextInt();
        scanner.nextLine(); // Clear the input buffer

        System.out.println("Enter total number of sequences : ");
        int n = scanner.nextInt();
        
        int[] locations = new int[n];
        
        System.out.println("Enter locations : ");
        for(int i=0 ; i<n ; i++) {
        	locations[i] = scanner.nextInt();
        }
        
        System.out.println("Enter direction to serve first(left,right) : ");
        String direction = scanner.next();

		CSCAN(locations, HeadPosition , direction);
	}

}
