package thread;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final Semaphore[] chopsticks = new Semaphore[NUM_PHILOSOPHERS];

    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            new Thread(new Philosopher(i)).start();
        }
    }

    private static class Philosopher implements Runnable {

        private final int id;

        public Philosopher(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                think();
                eat();
            }
        }

        private void think() {
            System.out.println("Philosopher " + id + " is thinking");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }
        }

        private void eat() {
            System.out.println("Philosopher " + id + " is eating");
            try {
                chopsticks[id].acquire();
                chopsticks[(id + 1) % NUM_PHILOSOPHERS].acquire();

                // eat for a while
                Thread.sleep(1000);

                chopsticks[id].release();
                chopsticks[(id + 1) % NUM_PHILOSOPHERS].release();
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
}
