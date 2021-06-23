package session11;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterThreadSafeExample {


    private AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        CounterThreadSafeExample obj = new CounterThreadSafeExample();

        int numberOfThreads=1000;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    obj.counter.getAndIncrement();
                }
                countDownLatch.countDown();

            }).start();
        }

        countDownLatch.await();

        System.out.println("obj.counter = " + obj.counter);
    }


}
