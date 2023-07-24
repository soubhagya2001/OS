package roundRobin;

import java.util.*;

class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int tempBt;
    int startTime;
    int completionTime;
    int waitingTime;
    int turnAroundTime;

public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.tempBt = burstTime ;
    }
}

public class RoundRobinAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();

        System.out.println("Enter Arrival and Burst Time ");
        for (int i = 1; i <= n; i++) {
           System.out.println("For process "+(i+1)+" : ");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();

            processes.add(new Process(i, arrivalTime, burstTime));
        }

        System.out.print("Enter the time quantum: ");
        int timeQuantum = scanner.nextInt();

        scanner.close();

        calculateRoundRobin(processes, timeQuantum);

        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time \tWaiting Time \tTurnaround Time");

        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        for (Process process : processes) {
            System.out.println(process.id + "\t\t" + process.arrivalTime + "\t\t" + process.tempBt + "\t\t\t" 
        + process.completionTime + "\t\t\t" + process.waitingTime +
                    "\t\t\t" + process.turnAroundTime);

            totalWaitingTime += process.waitingTime;
            totalTurnAroundTime += process.turnAroundTime;
        }

        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnAroundTime = (double) totalTurnAroundTime / n;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnAroundTime);
    }

    public static void calculateRoundRobin(List<Process> processes, int timeQuantum) {
        Queue<Process> queue = new LinkedList<>(processes);
        int currentTime = 0;

        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();

            if (currentProcess.burstTime <= timeQuantum) {
                currentProcess.startTime = currentTime;
                currentTime += currentProcess.burstTime;
                currentProcess.completionTime = currentTime;
                currentProcess.turnAroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnAroundTime - currentProcess.tempBt;
            } else {
                currentProcess.startTime = currentTime;
                currentTime += timeQuantum;
                currentProcess.burstTime -= timeQuantum;

                while (true) {
                    Process nextProcess = queue.peek();
                    if (nextProcess == null || nextProcess.arrivalTime > currentTime)
                        break;

                    queue.poll();
                    queue.offer(currentProcess);
                    currentProcess = nextProcess;

                    if (currentProcess.burstTime <= timeQuantum) {
                        currentProcess.startTime = currentTime;
                        currentTime += currentProcess.burstTime;
                        currentProcess.completionTime = currentTime;
                        currentProcess.turnAroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                        currentProcess.waitingTime = currentProcess.turnAroundTime - currentProcess.tempBt;
                        break;
                    } else {
                        currentProcess.startTime = currentTime;
                        currentTime += timeQuantum;
                        currentProcess.burstTime -= timeQuantum;
                    }
                }

                if (currentProcess.burstTime > 0) {
                    queue.offer(currentProcess);
                }
            }
        }
    }
}
