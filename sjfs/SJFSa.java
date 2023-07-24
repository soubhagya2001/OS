package sjfs;

import java.util.*;

class Task {
    String name;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnaroundTime;

    Task(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
    
    public int getArrivalTime() {
		return arrivalTime;
	}
    
    

	public int getBurstTime() {
		// TODO Auto-generated method stub
		return 0;
	}
}

public class SJFSa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();

        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= numTasks; i++) {
            System.out.println("Enter details for Task " + i + ":");
            System.out.print("Task Name: ");
            String name = scanner.next();
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            tasks.add(new Task(name, arrivalTime, burstTime));
        }

        // Sort tasks based on arrival time (if same arrival time, the shorter burst time comes first)
        Collections.sort(tasks, Comparator.comparing(Task::getArrivalTime).thenComparing(Task::getBurstTime));

        int currentTime = 0;
        for (Task task : tasks) {
            if (task.getArrivalTime() > currentTime) {
                currentTime = task.getArrivalTime();
            }
            task.waitingTime = currentTime - task.getArrivalTime();
            task.turnaroundTime = task.waitingTime + task.getBurstTime();
            currentTime += task.getBurstTime();
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        System.out.println("\nTask\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Task task : tasks) {
            System.out.println(task.name + "\t\t" + task.arrivalTime + "\t\t" + task.burstTime + "\t\t" +
                    task.waitingTime + "\t\t" + task.turnaroundTime);
            totalWaitingTime += task.waitingTime;
            totalTurnaroundTime += task.turnaroundTime;
        }

        double averageWaitingTime = totalWaitingTime / numTasks;
        double averageTurnaroundTime = totalTurnaroundTime / numTasks;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);

        scanner.close();
    }
}