package diskScheduling.SSTF;
import java.util.Scanner;



import java.util.ArrayList;
class Disk{
	int id ;
	int request;
	int seekTime;
	Boolean completed;
	
	Disk(int id , int request){
		this.id = id ;
		this.request = request;
		this.seekTime= 0;
		this.completed = false;
	}
}

public class SSTF1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter initial header position : ");
		int head = sc.nextInt();
		
		ArrayList<Disk> list = new ArrayList<Disk>();
		System.out.println("Enter number of request : ");
		int request = sc.nextInt();
		
		System.out.println("Enter disk request : ");
		for(int i =0 ; i<request ; i++) {
			list.add(new Disk(i+1,sc.nextInt()));
		}
		
		int completedRequest = 0;
		System.out.println("Location\tSeek Time");
		while(completedRequest < request) {
			Disk selectedRequest = null;
			int min = Integer.MAX_VALUE;
			
			for(Disk d : list) {
				if(Math.abs(head - d.request) < min && !d.completed) {
					selectedRequest = d;
					min = Math.abs(head - d.request);
				}
			}
			
			
			selectedRequest.seekTime = Math.abs(head - selectedRequest.request);
			selectedRequest.completed = true;
			head = selectedRequest.request;
			completedRequest++;
			System.out.println(selectedRequest.request+"\t\t"+selectedRequest.seekTime);
		}

	}

}
