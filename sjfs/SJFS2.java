package sjfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Process1 {
    private String name;
    private int arrivalTime;
    private int burstTime;

    public Process1(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }
}

class ProcessComparator implements Comparator<Process1> {
    @Override
    public int compare(Process1 p1, Process1 p2) {
        if (p1.getBurstTime() < p2.getBurstTime())
            return -1;
        else if (p1.getBurstTime() > p2.getBurstTime())
            return 1;
        else
            return 0;
    }
}

public class SJFS2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Process1> processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        System.out.println("Enter process Arrival & Burst time :");
        for (int i = 0; i < n; i++) {
            System.out.println("For Process " + (i) + ":");        
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();

            processes.add(new Process1("P"+i, arrivalTime, burstTime));
        }

        // Sort the processes based on burst time using SJF
        Collections.sort(processes, new ProcessComparator());

        System.out.println("\nProcess execution order:");
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

       System.out.println("PID\t\t"+"Arrival\t\tBurst\t\t\\Waiting\t\tTAT");
        for (Process1 process : processes) {
            int waitingTime = currentTime - process.getArrivalTime();
            if (waitingTime < 0)
                waitingTime = 0;

            int turnaroundTime = waitingTime + process.getBurstTime();

            System.out.println(process.getName() + "\t\t " +process.getArrivalTime()+"\t\t"+process.getBurstTime()+"\t\t"+ waitingTime + "\t\t " + turnaroundTime);

            currentTime += process.getBurstTime();
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
        }

        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);

        scanner.close();
    }
}
