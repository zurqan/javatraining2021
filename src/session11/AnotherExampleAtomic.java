package session11;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AnotherExampleAtomic {


    private AtomicInteger value= new AtomicInteger();

    private AtomicReference<Integer[]> array=new AtomicReference<>(new Integer[10]);

    public static void main(String[] args) throws InterruptedException {
        AnotherExampleAtomic obj = new AnotherExampleAtomic();
        int numberOfThreads=100;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
//                    synchronized (obj){
                    int newValue = obj.value.incrementAndGet();
                    Arrays.fill(obj.array.get(),newValue);

                    Integer arrayElementValue = obj.array.get()[0];
                    if(newValue!= arrayElementValue){
                        System.out.printf( "Strange value is %d while array filled with %d\n",newValue,arrayElementValue);
                    }
//                    }
                }
                countDownLatch.countDown();

            }).start();
        }

        countDownLatch.await();


    }
}
