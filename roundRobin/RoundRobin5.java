package roundRobin;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

class Process5{
	int processId;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnAroundTime;
    int remainingTime;
    int completedTime;
    Boolean completed = false;

    public Process5(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
        this.remainingTime = burstTime;
        this.completedTime = 0;
}
}

public class RoundRobin5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Process5> processList = new ArrayList<Process5>();
		int completedProcess = 0;
		int currentTime = 0;
		Deque<Process5> q = new ArrayDeque<Process5>();
		int timeQuantum = 0;
		int totalWaitingTime = 0;
		int totalTurnAroundTime = 0;
		
		System.out.print("Enter number of process : ");
		int n = sc.nextInt();
		System.out.println("Enter Arrival and Burst Time ");
		for(int i =0 ; i<n ; i++) {
			System.out.println("for process " + (i+1) + " : ");
			int aT  = sc.nextInt();
			int bT = sc.nextInt();
			processList.add(new Process5(i+1 , aT , bT ));
		}
		System.out.println("Enter TimeQuantum : ");
		timeQuantum = sc.nextInt();
		
		System.out.println("PID\t\tArrival\t\tBurst\t\tCompleition\t\tWaiting\t\tTurnAround");
		while(completedProcess < n) {
			Process5 selectedProcess = null;
			for(Process5 p : processList) {
				if(p.arrivalTime <= currentTime && !p.completed) {
					q.add(p);
				}
			}
			

		    if (!q.isEmpty()) {
		        selectedProcess = q.pollFirst();
		        if (selectedProcess.remainingTime <= timeQuantum) {
		            currentTime += selectedProcess.remainingTime;
		            selectedProcess.remainingTime = 0;
		        } else {
		            currentTime += timeQuantum;
		            selectedProcess.remainingTime -= timeQuantum;
		        }

		        selectedProcess.completedTime = currentTime + selectedProcess.burstTime;
		        selectedProcess.turnAroundTime = selectedProcess.completedTime - selectedProcess.arrivalTime;
		        selectedProcess.waitingTime = selectedProcess.turnAroundTime - selectedProcess.burstTime;
		        totalTurnAroundTime += selectedProcess.turnAroundTime;
		        totalWaitingTime += selectedProcess.waitingTime;

		        if (selectedProcess.remainingTime == 0) {
		            selectedProcess.completed = true;
		            completedProcess++;
		        } else {
		            q.add(selectedProcess);
		        }
		    } else {
		        currentTime++; // No process to execute, increment time
		    }
		}
		for(Process5 p : processList) {
			System.out.println(p.processId+"\t\t"+p.arrivalTime+"\t\t"+p.burstTime+"\t\t"+p.completedTime+"\t\t\t"+p.waitingTime+"\t\t"+p.turnAroundTime);
		}
		System.out.println("Average Waiting Time : "+ (float)totalWaitingTime/n);
		System.out.println("Average TurnAround Time : " + (float)totalTurnAroundTime/n);
	}

}

