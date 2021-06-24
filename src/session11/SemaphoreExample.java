package session11;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreExample {

    Semaphore d = new Semaphore(4);
    AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        SemaphoreExample obj = new SemaphoreExample();
        for (int i = 0; i < 7; i++) {
            Thread thread = new Thread(() -> {
                try {
                    obj.doAction(1);
//                    obj.test(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("#" + (i + 1));
            thread.start();
        }

    }

    public synchronized void test(int n) throws InterruptedException {
        System.out.printf("Thread %s Action in progress ... wait 6 seconds before releasing.. \n"
                , Thread.currentThread().getName());
        if (n > 0) {
            test(n - 1);
        }

        Thread.sleep(3000);


    }

    public void doAction(int n) throws InterruptedException {
        int actionNo = counter.incrementAndGet();

//        synchronized (d) {
//            System.out.println("d.availablePermits() = " + d.availablePermits());
//        }

//        if (n > 0)
            d.acquire();

        /// op 6 seconds
        System.out.printf("Thread %s Action in progress ... wait 6 seconds before releasing.. %d\n"
                , Thread.currentThread().getName(),
                actionNo);
//        if (n > 0) doAction(n - 1);
        Thread.sleep(6000);
        d.release();



    }
}
