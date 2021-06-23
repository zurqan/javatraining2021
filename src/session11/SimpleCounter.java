package session11;

import java.util.concurrent.CountDownLatch;

public class SimpleCounter {

    private int count;

    private static int staticCounter;
//
//    public  void inc() {
//        count++;
//    }
//
//    public  void dec() {
//        count --;
//    }

    public static void incStatic() {
        staticCounter++;
    }

//    public static synchronized void incStatic() {
//        staticCounter++;
//    }

    public void decStatic() {
        staticCounter--;
    }

    public synchronized void inc() {
        count++;
    }

    public synchronized void dec() {
        count--;
    }

    //    public void incAndDec(){
//        count++;
//        count--;
//    }
    public synchronized void incAndDec() {
        count++;
        count--;
        if (count != 0) {
            System.out.printf("inside incAndDec Strange value is %d\n", count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleCounter simpleCounter = new SimpleCounter();
        SimpleCounter simpleCounter2 = new SimpleCounter();

//        simpleCounter.inc();
//        simpleCounter.inc();
//        System.out.println("simpleCounter.count = " + simpleCounter.count);

        int numberOfThreads = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads*2);

        for (int i = 0; i < numberOfThreads; i++) {

            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {

//                    simpleCounter.inc();
//                    simpleCounter.dec();

                    synchronized (simpleCounter){

                        simpleCounter.inc();
                        simpleCounter.dec();

//                    simpleCounter.incAndDec();

                        int count = simpleCounter.count;
                        if (count != 0) {
                            System.out.printf("Strange value is %d\n", count);
                        }
                    }

                    synchronized (SimpleCounter.class){


                        SimpleCounter.incStatic();
                    }
                }
                countDownLatch.countDown();

            }).start();

            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    simpleCounter2.incAndDec();
//                    SimpleCounter.incStatic();
                    synchronized (SimpleCounter.class){


                        SimpleCounter.incStatic();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }




        countDownLatch.await();
        System.out.println("simpleCounter.count = "+simpleCounter.count);
        System.out.println("simpleCounter2.count = " + simpleCounter2.count);
        System.out.println("SimpleCounter.staticCounter = "+SimpleCounter.staticCounter);


}
}
