package systemCalls;

import java.util.concurrent.TimeUnit;

public class SignalHandlerExample {
    public static void main(String[] args) {
        SignalHandlerExample example = new SignalHandlerExample();
        example.setupSignalHandler();

        while (true) {
            System.out.println("Going to sleep for a second...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupSignalHandler() {
        SignalHandler signalHandler = new SignalHandler();
        SignalHandler.SignalHandlerThread signalHandlerThread = new SignalHandler.SignalHandlerThread(signalHandler);
        Runtime.getRuntime().addShutdownHook(signalHandlerThread);
    }

    private static class SignalHandler implements Runnable {
        @Override
        public void run() {
            System.out.println("Caught signal, coming out...");
            System.exit(1);
        }

        private static class SignalHandlerThread extends Thread {
            private final SignalHandler signalHandler;

            SignalHandlerThread(SignalHandler signalHandler) {
                this.signalHandler = signalHandler;
            }

            @Override
            public void run() {
                signalHandler.run();
            }
        }
    }
}

