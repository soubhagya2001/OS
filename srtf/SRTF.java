package srtf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class Process
{
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time
     
    public Process(int pid, int bt, int art)
    {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}
 
public class SRTF
{
    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(List<Process> proc, int n,
                                     int wt[],int ct[])
    {
        int rt[] = new int[n];
      
        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc.get(i).bt;
      
        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
      
        // Process until all processes gets
        // completed
        while (complete != n) {
      
            // Find process with minimum burst time(minimum
            // remaining time) among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++)
            {
                if ((proc.get(j).art <= t) &&
                  (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }
      
            if (check == false) {
                t++;
                continue;
            }
      
            // Reduce remaining time by one
            rt[shortest]--;
      
            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;
      
            // If a process gets completely
            // executed
//            if (rt[shortest] == 0) {
//      
//                // Increment complete
//                complete++;
//                check = false;
//      
//                // Find finish time of current
//                // process
//                finish_time = t + 1;
//      
//                
//                //compleition time
//                ct[shortest] = finish_time;
//                
//                // Calculate waiting time
//                wt[shortest] = finish_time -
//                		proc.get(shortest).bt -
//                		proc.get(shortest).art;
//      
//                if (wt[shortest] < 0)
//                    wt[shortest] = 0;
//            }
            
            if (rt[shortest] == 0) {
                // Increment complete
                complete++;
                check = false;
                // Find finish time of current process
                finish_time = t + 1;
                // Completion time
                ct[shortest] = finish_time;
                // Calculate waiting time
                wt[shortest] = finish_time - proc.get(shortest).bt - proc.get(shortest).art;
                if (wt[shortest] < 0)
                    wt[shortest] = 0;
                check = false; // Reset check to false
            }
            // Increment time
            t++;
        }
    }
      
    // Method to calculate turn around time
    static void findTurnAroundTime(List<Process> proc, int n,
                            int wt[], int tat[])
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc.get(i).bt + wt[i];
    }
      
    // Method to calculate average time
    static void findavgTime(List<Process> processes, int n)
    {
        int wt[] = new int[n], tat[] = new int[n];
        int  total_wt = 0, total_tat = 0;
        int ct[] = new int[n];
      
        // Function to find waiting time of all
        // processes
        findWaitingTime(processes, n, wt, ct);
      
        // Function to find turn around time for
        // all processes
        findTurnAroundTime(processes, n, wt, tat);
      
        // Display processes along with all
        // details
        System.out.println("Processes     " + " Arrival \t"+
                           " Burst time\t " + "Compleition \t" +
                           " Waiting time\t " +
                           " Turn around time");
      
        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + processes.get(i).pid + "\t\t"+processes.get(i).art+"\t\t" +
                             + processes.get(i).bt + "\t\t "+ct[i]+"\t\t" + wt[i]
                             + "\t\t" + tat[i]);
        }
      
        System.out.println("Average waiting time = " +
                          (float)total_wt / (float)n);
        System.out.println("Average turn around time = " +
                           (float)total_tat / (float)n);
        
        System.out.flush(); 
    }
     
    // Driver Method
    public static void main(String[] args)
    {
//         Process proc[] = { new Process(0, 5, 0),
//                            new Process(1, 3, 1),
//                            new Process(2, 2, 3),
//                            new Process(3, 7, 3),
//                            new Process(2, 4, 4),
//                            new Process(2, 1, 7)};
         
    	Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();

        System.out.println("Enter Arrival , Burst : ");
        for (int i = 0; i < n; i++) {
            System.out.println("for process " + (i + 1) + ":");
            
            int arrivalTime = scanner.nextInt();
          
            int burstTime = scanner.nextInt();
            scanner.nextLine();
            

            Process process = new Process(i + 1, arrivalTime, burstTime);
            processes.add(process);
        }
         
         findavgTime(processes, processes.size());
    }
}
