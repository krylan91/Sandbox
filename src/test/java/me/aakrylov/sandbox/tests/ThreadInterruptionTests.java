package me.aakrylov.sandbox.tests;

public class ThreadInterruptionTests {

    static class InterruptedSleepingRunner implements Runnable {
        @Override
        public void run() {
            doAPseudoHeavyWeightJob();
        }

        private void doAPseudoHeavyWeightJob() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                // You are kidding me
                System.out.println(i + " " + i * 2);
                // Let me sleep <evil grin>
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread interrupted\n Exiting...");
                    break;
                } else {
                    sleepBabySleep();
                }
            }
        }

        protected void sleepBabySleep() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                System.out.println("Interrupted");
                // Without it program will continue running till the Integer.MAX_VALUE iteration are done.
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptedSleepingRunner());
        thread.start();
        // Giving 10 seconds to finish the job.
        Thread.sleep(10000);
        // Let me interrupt
        thread.interrupt();
    }
}
