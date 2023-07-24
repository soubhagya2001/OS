package fcfs;

import java.util.*;

class Process3 {
    int id;
    int arrivalTime;
    int burstTime;
    int startTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;
    boolean completed ;
  

    public Process3(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.startTime = 0;
        this.completionTime = 0;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.completed = false;
    }
}

public class Fcfs2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process3> processes = new ArrayList<>();

        System.out.println("Enter Arrival , Burst");
        for (int i = 0; i < n; i++) {
            System.out.println("for process " + (i + 1) + ":");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();

            Process3 process = new Process3(i + 1, arrivalTime, burstTime);
            processes.add(process);
        }
        
        processes.sort(Comparator.comparingInt((Process3 p) -> p.arrivalTime));
        
        int currentTime = 0;
        int completedProcesses = 0;
        int i = 0;
        while(completedProcesses < n) {
        	Process3 selectedProcess = null;
        	
        	
            if(processes.get(i).arrivalTime <= currentTime && processes.get(i).completed == false ) {
            	selectedProcess = processes.get(i);
            	
            }

            if (selectedProcess == null) {
                currentTime++;
                continue;
            }

           selectedProcess.startTime = currentTime;
            selectedProcess.completionTime = selectedProcess.startTime + selectedProcess.burstTime;
            currentTime += selectedProcess.burstTime;
            selectedProcess.turnaroundTime = selectedProcess.completionTime - selectedProcess.arrivalTime;

            selectedProcess.waitingTime = selectedProcess.turnaroundTime - selectedProcess.burstTime;
            selectedProcess.completed = true;
            
            completedProcesses++;
            i++;
        }
        
        float AverageWaitingTime = 0f , AverageTurnAroundTime = 0f;
        System.out.println("\nProcess\t\tArrival\t\tBurst\t\tStart\t\tCompletion\tWaiting\tTurnaround");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        for (Process3 process : processes) {
        	AverageTurnAroundTime += process.turnaroundTime;
        	AverageWaitingTime += process.waitingTime;
            System.out.println(process.id + "\t\t" + process.arrivalTime + "\t\t" + process.burstTime + "\t\t"+ process.startTime + "\t\t"
                    + process.completionTime + "\t\t" + process.waitingTime +
                    "\t\t" + process.turnaroundTime);
        }
        
        System.out.println("Average Waiting Time : " + AverageWaitingTime/n);
        System.out.println("Average TurnAround Time : " + AverageTurnAroundTime/n);

        scanner.close();

	}

}
