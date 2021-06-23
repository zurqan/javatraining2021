package session11;

import java.util.concurrent.CountDownLatch;

public class AnotherVolatileExample {

    private volatile int count;

    public static void main(String[] args) throws InterruptedException {
        AnotherVolatileExample obj = new AnotherVolatileExample();

        int numberOfThreads = 1000;

        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(()->{

                for (int j = 0; j < 10000; j++) {

//                    obj.count=obj.count+1;
                    obj.count++;
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("obj.count = " + obj.count);
    }
}
