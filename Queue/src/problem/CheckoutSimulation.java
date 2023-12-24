package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import queue.SQueue;

public class CheckoutSimulation {
     public static void main(String[] args) {
        int simLength = 60;

        SQueue<Integer> queue = new SQueue<>();
        int totalCustomersServed = 0;
        int totalWaitingTime = 0;
        int longestWait = 0;

        Random random = new Random();

        for (int minute = 0; minute < simLength; minute++) {
            // Serve a customer if the queue is not empty
            if (!queue.isEmpty()) {
                int waitTime = minute - queue.dequeue();
                totalWaitingTime += waitTime;
                longestWait = Math.max(longestWait, waitTime);
                totalCustomersServed++;
            }

            // Add new customers to the queue
            int k = random.nextInt(4);
            if (k == 1) {
                queue.enqueue(minute);
            } else if (k == 2) {
                queue.enqueue(minute);
                queue.enqueue(minute);
            }
        }

        // Calculate and print statistics
        double averageWait = (double) totalWaitingTime / totalCustomersServed;
        System.out.println("Total number of customers served: " + totalCustomersServed);
        System.out.println("Average wait: " + averageWait);
        System.out.println("Longest wait: " + longestWait);
    }
}
