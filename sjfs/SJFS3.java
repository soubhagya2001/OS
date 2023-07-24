package sjfs;

import java.util.*;

public class SJFS3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get the number of processes
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();

        // Create an array to store the process IDs and burst times
        int[][] processes = new int[n][3];

        System.out.println("Enter Arrival and burst time ");
        // Get the process IDs and burst times from the user
        for (int i = 0; i < n; i++) {
            System.out.println("for process " + (i + 1) + ": ");
            processes[i][0] = i+1;
            processes[i][2] = sc.nextInt();
            processes[i][1] = sc.nextInt();
        }

        // Sort the processes in ascending order of burst time
        Arrays.sort(processes, (a, b) -> a[1] - b[1]);

        // Calculate the waiting time and turnaround time for each process
        int[] waitingTimes = new int[n];
        int[] turnaroundTimes = new int[n];
        for (int i = 0; i < n; i++) {
            waitingTimes[i] = i == 0 ? 0 : waitingTimes[i - 1] + processes[i][1];
            turnaroundTimes[i] = waitingTimes[i] + processes[i][1];
        }

        // Print the process IDs, burst times, waiting times, and turnaround times
        System.out.println("Process ID | Burst Time | Waiting Time | Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i][0] + "\t |\t " + processes[i][1] + "\t |\t " + waitingTimes[i] + "\t |\t " + turnaroundTimes[i]);
        }
    }
}
