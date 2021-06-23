package session11;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LatchExample {

    private int count;

    public static void main(String[] args) throws InterruptedException {

        LatchExample obj = new LatchExample();

        int numberOfThreads = 10;

        CountDownLatch startDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(() -> {
                System.out.println("I am in a wait state");
                try {
                    startDownLatch.await();

                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread started");
                atomicInteger.getAndIncrement();
                countDownLatch.countDown();
            }).start();
        }

        Thread.sleep(4000);
        System.out.println("I will start all threads now");
        startDownLatch.countDown();
//        countDownLatch.await();
        countDownLatch.await(3, TimeUnit.SECONDS);
        System.out.println("atomicInteger.get() = " + atomicInteger.get());
        System.out.println("Done!");
    }
}
