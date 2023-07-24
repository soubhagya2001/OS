package roundRobin;

//Java Code
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class RoundRobin2 {

//At every time quantum or when a process has been
//executed before the time quantum, check for any new
//arrivals and push them into the queue
public static void checkForNewArrivals(Process[] processes, int n,
						int currentTime,
						Queue<Integer> readyQueue)
{
	for (int i = 0; i < n; i++) {
		Process p = processes[i];
	// checking if any processes has arrived
	// if so, push them in the ready Queue.
		if (p.arrivalTime <= currentTime && !p.inQueue
				&& !p.isComplete) {
			processes[i].inQueue = true;
			readyQueue.add(i);
		}
	}
}

//Context switching takes place at every time quantum
//At every iteration, the burst time of the processes
//in the queue are handled using this method
public static void
	updateQueue(Process[] processes, int n, int quantum,
				Queue<Integer> readyQueue, int currentTime,
				int programsExecuted)
{
	int i = readyQueue.remove();

	// if the process is going to be finished executing,
	// ie, when it's remaining burst time is less than
	// time quantum mark it completed and increment the
	// current time and calculate its waiting time and
	// turnaround time
	if (processes[i].burstTimeRemaining <= quantum) {
	processes[i].isComplete = true;
	currentTime += processes[i].burstTimeRemaining;
	processes[i].completionTime = currentTime;
	processes[i].waitingTime
		= processes[i].completionTime
		- processes[i].arrivalTime
		- processes[i].burstTime;
	processes[i].turnaroundTime
		= processes[i].waitingTime
		+ processes[i].burstTime;

	if (processes[i].waitingTime < 0)
		processes[i].waitingTime = 0;

	processes[i].burstTimeRemaining = 0;

	// if all the processes are not yet inserted in
	// the queue, then check for new arrivals
	if (programsExecuted != n) {
		checkForNewArrivals(
		processes, n, currentTime, readyQueue);
	}
	}
	else {
	// the process is not done yet. But it's going
	// to be pre-empted since one quantum is used
	// but first subtract the time the process used
	// so far
	processes[i].burstTimeRemaining -= quantum;
	currentTime += quantum;

	// if all the processes are not yet inserted in
	// the queue, then check for new arrivals
	if (programsExecuted != n) {
		checkForNewArrivals(
		processes, n, currentTime, readyQueue);
	}
	// insert the incomplete process back into the
	// queue
	readyQueue.add(i);
	}
}

//Just a function that outputs the result in terms of
//their PID.
public static void output(Process[] processes, int n)
{
	double avgWaitingTime = 0;
	double avgTurntaroundTime = 0;
	// sort the processes array by processes.PID
	Arrays.sort(processes, (Process p1, Process p2) -> {
	return p1.pid - p2.pid;
	});

	System.out.println("Process\tArrival\tBurst\tCompletion\tWaiting\tTurnAround");
	for (int i = 0; i < n; i++) {
		System.out.println(processes[i].pid + "\t\t"+
		+ processes[i].arrivalTime +"\t\t"+ processes[i].burstTime+"\t\t"+processes[i].completionTime+"\t\t"
		+ processes[i].turnaroundTime);
		
		avgWaitingTime += processes[i].waitingTime;
		avgTurntaroundTime += processes[i].turnaroundTime;
	}
	System.out.println("Average Waiting Time: "+ avgWaitingTime / n);
	System.out.println("Average Turnaround Time: "+ avgTurntaroundTime / n);
}

/*
	* This function assumes that the processes are already
	* sorted according to their arrival time
	*/
public static void roundRobin(Process[] processes,
								int n, int quantum)
{
	Queue<Integer> readyQueue
	= new LinkedList<Integer>();
	readyQueue.add(0); // initially, pushing the first
	// process which arrived first
	processes[0].inQueue = true;

	int currentTime = 0; // holds the current time after each
	// process has been executed
	int programsExecuted = 0; // holds the number of programs executed so
	// far

	while (!readyQueue.isEmpty()) {
	updateQueue(processes, n, quantum, readyQueue,
				currentTime, programsExecuted);
	}
}

public static class Process {
	int pid;
	int arrivalTime;
	int burstTime;
	int burstTimeRemaining; // the amount of CPU time
	// remaining after each
	// execution
	int completionTime;
	int turnaroundTime;
	int waitingTime;
	boolean isComplete;
	boolean inQueue;
}

public static void main(String[] args)
{
	Scanner sc = new Scanner(System.in);
	int n, quantum;

	System.out.println("Enter the number of processes: ");
	n = sc.nextInt();
	System.out.println("Enter time quantum: ");
	quantum = sc.nextInt();
	
	Process[] processes = new Process[n + 1];

	System.out.println("Enter arrival time and burst time ");
	for (int i = 0; i < n; i++) {
	System.out.println("For process "+ (i + 1) + ": ");
	processes[i].arrivalTime = sc.nextInt();
	processes[i].burstTime = sc.nextInt();
	processes[i].burstTimeRemaining
		= processes[i].burstTime;
	processes[i].pid = i + 1;
	System.out.println();
	}

	// stl sort in terms of arrival time
	Arrays.sort(processes, (Process p1, Process p2) -> {
	return p1.arrivalTime - p2.arrivalTime;
	});

	roundRobin(processes, n, quantum);

	output(processes, n);
}
}


