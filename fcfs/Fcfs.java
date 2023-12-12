package fcfs;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class Process implements Comparable<Process> {
	int index;
	int arrTime;
	int burstTime;
	
	Process(int i,int a , int b){
		this.arrTime = a;
		this.burstTime = b;
		this.index = i;
	}

	@Override
	public int compareTo(Process o) {
		// TODO Auto-generated method stub
		return this.arrTime - o.arrTime;
	}	
}

public class Fcfs {

	public static void avgWaitTime(ArrayList<Process> process) {
		int wt[] = new int[process.size()]; 
		int tat[] = new int[process.size()]; 
		int ct[] = new int[process.size()];
		int st[] = new int[process.size()];
		
		float totalWaitingTime = 0, totalTurnAroundTime = 0;
		startingTime(process,st);
		compleitionTime(process, ct,st);
		tat(process,ct,tat);
		waitingTime(process,tat,wt);
//		waitingTime(process , wt);
//		turnAroundTime(process , wt , tat);
		
	 System.out.println("\nProcesses ||" + " Arrival Time ||" + " Burst Time || Starting Time ||" +  " Waiting Time || Compleition Time ||" + " Turn-Around Time ");
	 
	 for(int i=0;i<process.size();i++) {
		 totalWaitingTime += wt[i];
		 totalTurnAroundTime += tat[i];
		 System.out.println( (process.get(i).index +1)+ "\t\t"+ process.get(i).arrTime+"\t\t"+process.get(i).burstTime+"\t\t"+st[i]+"\t\t"+wt[i]+"\t\t"+ct[i]+"\t\t"+tat[i] );
	 }
	 System.out.println("Average Waiting Time : "+ totalWaitingTime/process.size());
	 System.out.println("Average TurnAroundTime : " + totalTurnAroundTime/process.size());
	 System.out.println("Throughput : " + (float)process.size()/ct[ct.length-1]);

	}
	
	//starting time calculate
	public static void startingTime(ArrayList<Process> process, int[] st) {
		st[0] = process.get(0).arrTime;
		for(int i=1;i<process.size();i++) {
			st[i] = st[i-1] + process.get(i-1).burstTime;
		}
	}
	
	//compleition time calculate
	public static void compleitionTime(ArrayList<Process> process, int[] ct, int[] st) {
		for(int i= 0;i<process.size();i++) {
			ct[i] = st[i]+ process.get(i).burstTime;
		}
	}
	
	//turn around time calculate
	public static void tat(ArrayList<Process> process, int[] ct, int[] tat) {
		for(int i=0;i<process.size();i++) {
			tat[i] = ct[i] - process.get(i).arrTime;
		}
	}
	
	//waiting time calculate
	public static void waitingTime(ArrayList<Process> process,int[] tat, int[] wt) {
		for(int i= 0;i<process.size();i++) {
			wt[i] = tat[i] - process.get(i).burstTime;
		}
	}
	
//	public static void waitingTime(ArrayList<Process> process , int[] wt) {
//		 //To store the burst time of previous process.
//        int temp[] = new int[wt.length+1];
//        temp[0] = 0;
//        
//        for (int i = 0; i < wt.length; i++) {
//            wt[i] = 0;
//            temp[i + 1] = temp[i] + process.get(i).burstTime;
//
//            //Calculating waiting time
//            wt[i] = temp[i] - process.get(i).arrTime;
//        }
//	}
//	
//	public static void turnAroundTime(ArrayList<Process> process , int[] wt , int[] tat) {
//		 for (int i = 0; i < wt.length; i++) {
//	            //Calculating turn around time
//	            tat[i] = (process.get(i).arrTime) + (process.get(i).burstTime) ;
//	        }
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of processes : ");
		int n = sc.nextInt();
		
		ArrayList<Process> process = new ArrayList<>();
		
		System.out.println("Enter Arrival and Burst time");
		for(int i=0;i<n;i++) {
			System.out.print("For process "+(i+1)+" : ");
			process.add(new Process(i,sc.nextInt() , sc.nextInt()));
		}
		
		//sorting based on arrival
		Collections.sort(process);
		
		avgWaitTime(process);
		
		
		
		
		
		
		
	}

}
