package sjfs;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;





class Process2 {
    String name;
    int arrivalTime;
    int burstTime;
   // Boolean flag = false;
    
    public Process2(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}


//sort based on arrival time
class ArrivalComparator implements Comparator<Process2> {
    @Override
    public int compare(Process2 p1, Process2 p2) {
        if (p1.arrivalTime < p2.arrivalTime)
            return -1;
        else if (p1.arrivalTime > p2.arrivalTime)
            return 1;
        else
            return 0;
    }
}

//sort based on Burst time
class BurstComparator implements Comparator<Process2> {
  @Override
  public int compare(Process2 p1, Process2 p2) {
      if (p1.burstTime < p2.burstTime)
          return -1;
      else if (p1.burstTime > p2.burstTime)
          return 1;
      else
          return 0;
  }
}


//main class
public class SJFS4 {
	static ArrayList<Process2> processes = new ArrayList<>();
	static ArrayList<Process2> temp = new ArrayList<>();
	
	static int currentTime = 0;
	static int aTAT = 0;
	static int aWT = 0;
//	public static void calculate(ArrayList<Process1> process) {
//		int startingTime = currentTime;
//		int compleitionTime = startingTime + process.get(0).burstTime;
//		currentTime = currentTime + process.get(0).burstTime;
//		
//		int turnAroundTime = compleitionTime - process.get(0).arrivalTime;
//		int waitingTime = turnAroundTime - process.get(0).burstTime;
//		
//		System.out.println(process.get(0).name+"\t\t"+process.get(0).arrivalTime+"\t\t"+process.get(0).burstTime+"\t\t"+
//				startingTime+"\t\t"+compleitionTime+"\t\t"+turnAroundTime+"\t\t"+waitingTime);
//		
//		process.remove(0);
//	}
	
	public static void calculate(String name , int at,int bt) {
		int st = currentTime;
		int ct = st + bt;
		currentTime = currentTime + bt;
		
		int tat = ct - at;
		int wt = tat - bt;
		
		aTAT += tat;
		aWT += wt;
		System.out.println(name+"\t\t" + at +"\t\t"+ bt +"\t\t"+ st +"\t\t\t"+ ct +"\t\t\t"+ tat +"\t\t\t"+ wt );
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
	    

	    System.out.print("Enter the number of processes: ");
	    int n = sc.nextInt();

	    System.out.print("Enter process Arrival & Burst time :\n");
	    for (int i = 0; i < n; i++) {
	    	System.out.println("For Process " + (i+1) + ":");        
	    	int arrivalTime = sc.nextInt();
	    	int burstTime = sc.nextInt();

	    	processes.add(new Process2("P"+(i+1), arrivalTime, burstTime));
	    }

		//sort based on arrival time
	    Collections.sort(processes, new ArrivalComparator());
	    
	    
	    System.out.println("PID\t\t"+"Arrival\t\t"+"Burst\t\t"+"Starting\t\t"+"Compleition\t\t"+"TurnAround\t\t"+"Waiting");
	    //calculate 1st process
	    calculate(processes.get(0).name , processes.get(0).arrivalTime , processes.get(0).burstTime);
	    
	    processes.remove(0);
	    
	    //sort based on burst Time
	    //Collections.sort(processes, new BurstComparator());
	    
	    
	    while(!processes.isEmpty() || !temp.isEmpty()) {
	    //add process which has arrived into temporary list
	   // System.out.println("Current time : "+ currentTime);
	    if(!processes.isEmpty()) {	
	    	for(int i= 0;i<processes.size();i++) {
	    		if(processes.get(i).arrivalTime <= currentTime) {
	    		temp.add(processes.get(i));
	    		processes.remove(processes.get(i));

	    		}
	    	}
	    }
	    
	    if(!processes.isEmpty()) {	
	    	for(int i= 0;i<processes.size();i++) {
	    		if(processes.get(i).arrivalTime <= currentTime) {
	    		temp.add(processes.get(i));
	    		processes.remove(processes.get(i));

	    		}
	    	}
	    }
	    
	    
	   // System.out.println("Current time : "+ currentTime);
	   // System.out.println("main queue size : "+ processes.size());
	    //System.out.println("Ready queue size : "+ temp.size());
	    //sort based on burst time
	    Collections.sort(temp, new BurstComparator());
	    
	    
	    //process in the queue :
//	    System.out.print("Process in the queue : ");
//	    for(int i =0; i<temp.size();i++) {
//	    	System.out.print(temp.get(i).name+" ");
//	    }
//	    System.out.println();
	    
	    calculate(temp.get(0).name,temp.get(0).arrivalTime,temp.get(0).burstTime);
	   // System.out.println("Current time : "+ currentTime);
	    temp.remove(temp.get(0));
	    
	    }
	    System.out.println("Average Turn Around Time : " + (aTAT/n));
	    System.out.println("Average Waiting Time : "+ (aWT/n));
	    
	    
//	    while(!processes.isEmpty()) {
//	    for(Process1 p : processes) {
//	    	if(p.arrivalTime <= currentTime && p.flag == false) {
//	    		calculate(p.name , p.arrivalTime , p.burstTime);
//	    		p.flag = true;
//	    		processes.remove(p);
//	    	}
//	    }
//	   }
	}

}

