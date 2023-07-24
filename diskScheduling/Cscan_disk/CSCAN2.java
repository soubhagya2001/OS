package diskScheduling.Cscan_disk;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Collections;
class Disk{
	
	int request;
	int seekTime;
	Boolean completed;
	
	Disk( int request){
		
		this.request = request;
		this.seekTime= 0;
		this.completed = false;
	}
}
public class CSCAN2 {

	static void Left(int head , ArrayList<Disk> left , ArrayList<Disk> right) {
		for(Disk d : left) {
		
			d.seekTime = Math.abs(head - d.request);
			head = d.request;
			System.out.println(d.request+"\t\t"+d.seekTime);
			
			
			if(d.request == 0) {
				head = 200;
				System.out.println(200+"\t\t"+200);
			}
		}
		
		
		
	}
	
	static void Right(int head , ArrayList<Disk> left , ArrayList<Disk> right) {
		for(Disk d : right) {
			d.seekTime = Math.abs(head - d.request);
			head = d.request;
			System.out.println(d.request+"\t\t"+d.seekTime);
			
			if(d.request == 200) {
				head = 0;
				System.out.println(0+"\t\t"+200);
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter initial header position : ");
		int head = sc.nextInt();
		
		ArrayList<Disk> left = new ArrayList<Disk>();
		ArrayList<Disk> right = new ArrayList<Disk>();
		System.out.println("Enter number of request : ");
		int request = sc.nextInt();
		
		System.out.println("Enter disk request : ");
		for(int i =0 ; i<request ; i++) {
			int num = sc.nextInt();
			if(num <= head) {
				left.add(new Disk(num));
			}else {
				right.add(new Disk(num));
			}
		}
		
		System.out.println("Enter direction to travel first(left / right) : ");
		String direction = sc.next().toLowerCase();
		
		int completedRequest = 0;
		System.out.println("Location\tSeek Time");
		
		if(direction.equals("left")) {
			left.add(new Disk(0));
			Collections.sort(left, (disk1, disk2) -> Integer.compare(disk1.request, disk2.request));
			Collections.reverse(left);
			Collections.sort(right, (disk1, disk2) -> Integer.compare(disk1.request, disk2.request));
			Collections.reverse(right);
			Left(head , left , right);
			head = 200;
			Right(head , left , right);
		}else if(direction.equals("right")) {
			right.add(new Disk(200));
			Collections.sort(right, (disk1, disk2) -> Integer.compare(disk1.request, disk2.request));
			Collections.sort(left, (disk1 , disk2) -> Integer.compare(disk1.request, disk2.request));
			Right(head , left , right);
			Left(head , left , right);
		}else {
			System.out.println("Wrong Direction");
		}

	}

}
