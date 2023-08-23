package thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReaderWriterProblem {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static int sharedResource = 0;

    static class Reader extends Thread {
        public void run() {
            while (true) {
                lock.readLock().lock();
                System.out.println("Reader " + Thread.currentThread().getId() + " reads: " + sharedResource);
                lock.readLock().unlock();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Writer extends Thread {
        public void run() {
            while (true) {
                lock.writeLock().lock();
                int randomValue = (int) (Math.random() * 100); // Simulate writing some new data
                sharedResource = randomValue;
                System.out.println("Writer " + Thread.currentThread().getId() + " writes: " + sharedResource);
                lock.writeLock().unlock();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int numReaders = 3;
        int numWriters = 2;

        Reader[] readers = new Reader[numReaders];
        Writer[] writers = new Writer[numWriters];

        for (int i = 0; i < numReaders; i++) {
            readers[i] = new Reader();
            readers[i].start();
        }

        for (int i = 0; i < numWriters; i++) {
            writers[i] = new Writer();
            writers[i].start();
        }
    }
}

