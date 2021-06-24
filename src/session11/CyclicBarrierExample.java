package session11;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
            //all threads are done
            System.out.println("All Threads are done");
        });

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Job(cyclicBarrier));
            thread.setName("#"+(i+1));
            thread.start();
        }
    }

    private static class Job implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        private Job(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {

            Random random = new Random();
            int seconds = random.nextInt(10) + 2;

            String name = Thread.currentThread().getName();
            System.out.printf( "%s is started and need %d seconds to complete\n", name,seconds);

            try {
                Thread.sleep(seconds*1000);
                System.out.printf("%s waiting? %d parties %d waiting \n",name,cyclicBarrier.getParties(),cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
                System.out.printf("Done %s\n", name);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            //do other action

        }
    }
}
