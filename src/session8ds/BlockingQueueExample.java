package session8ds;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            //wait  sec
            queue.add(4);
            int seconds = 6;
            System.out.printf
             ("sleep for %d seconds \n",
                    seconds);
            sleep(seconds);
            queue.add(5);
        }).start();

        for (int i = 0; i < 3; i++) {
            System.out.println("Try to get an element");
            Integer number = queue.poll(5, TimeUnit.SECONDS);
            System.out.println("number = " + number);
        }
    }

    private static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {


        }
    }
}
