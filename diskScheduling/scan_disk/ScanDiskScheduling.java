package diskScheduling.scan_disk;
import java.util.ArrayList;
import java.util.Scanner;

public class ScanDiskScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Accepting input from the user
        System.out.print("Enter the initial head position: ");
        int initialHeadPosition = scanner.nextInt();
        scanner.nextLine(); // Clear the input buffer

        System.out.print("Enter the sequence list (comma-separated): ");
        String sequenceInput = scanner.nextLine();
        String[] sequenceArray = sequenceInput.split(",");

        System.out.print("Enter the direction (left or right): ");
        String direction = scanner.nextLine().toLowerCase();

        // Converting sequence list to integer array
        int[] sequence = new int[sequenceArray.length];
        for (int i = 0; i < sequenceArray.length; i++) {
            sequence[i] = Integer.parseInt(sequenceArray[i].trim());
        }

        // Finding the maximum track from the sequence manually
        int maxTrack = sequence[0];
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] > maxTrack) {
                maxTrack = sequence[i];
            }
        }

        // Calculating seek time for each request
        int totalSeekTime = 0;
        ArrayList<Integer> seekTimes = new ArrayList<>();

        if (direction.equals("left")) {
            for (int i = initialHeadPosition; i >= 0; i--) {
                if (i == initialHeadPosition) {
                    seekTimes.add(0);
                } else {
                    seekTimes.add(Math.abs(i - sequence[i]));
                    totalSeekTime += Math.abs(i - sequence[i]);
                }
            }

            for (int i = 0; i <= maxTrack; i++) {
                if (i == initialHeadPosition) {
                    seekTimes.add(0);
                } else {
                    seekTimes.add(Math.abs(i - sequence[i]));
                    totalSeekTime += Math.abs(i - sequence[i]);
                }
            }
        } else if (direction.equals("right")) {
            for (int i = initialHeadPosition; i <= maxTrack; i++) {
                if (i == initialHeadPosition) {
                    seekTimes.add(0);
                } else {
                    seekTimes.add(Math.abs(i - sequence[i]));
                    totalSeekTime += Math.abs(i - sequence[i]);
                }
            }

            for (int i = maxTrack; i >= 0; i--) {
                if (i == initialHeadPosition) {
                    seekTimes.add(0);
                } else {
                    seekTimes.add(Math.abs(i - sequence[i]));
                    totalSeekTime += Math.abs(i - sequence[i]);
                }
            }
        } else {
            System.out.println("Invalid direction entered. Please choose either 'left' or 'right'.");
            return;
        }

        // Printing seek time for each request
        System.out.println("Seek Time for Each Request:");
        for (int i = 0; i < sequence.length; i++) {
            System.out.println("Request: " + sequence[i] + " - Seek Time: " + seekTimes.get(i));
        }

        // Calculating and printing average seek time
        int totalRequests = sequence.length;
        double averageSeekTime = (double) totalSeekTime / totalRequests;
        System.out.println("Average Seek Time: " + averageSeekTime);

        // Calculating and printing throughput
        int totalTracks = maxTrack + 1;
        double throughput = (double) totalRequests / totalTracks;
        System.out.println("Throughput: " + throughput);
    }
}
