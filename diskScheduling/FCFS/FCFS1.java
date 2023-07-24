package diskScheduling.FCFS;
import java.util.Scanner;
import java.util.ArrayList;
class Disk{
	int id ;
	int request;
	int seekTime;
	
	Disk(int id , int request){
		this.id = id ;
		this.request = request;
		this.seekTime= 0;
	}
}
public class FCFS1 {

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
		
		System.out.println("Order\t\tSeek Time");
		for(Disk d : list) {
			d.seekTime = Math.abs(head - d.request);
			head = d.request;
			System.out.println(d.request+"\t\t"+d.seekTime);
		}
	}

}
