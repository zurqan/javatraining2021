package session11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureExample {

    FutureTask<Integer> future= new FutureTask<>(()->{
        System.out.println("My process need 5 seconds");
        Thread.sleep(5000);
        return 100;
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureExample obj = new FutureExample();
        new Thread(obj.future).start();

        System.out.println("Try to get future number");
//        Integer integer = obj.future.get();
        Integer integer = obj.future.get(4, TimeUnit.SECONDS);
        System.out.println("integer = " + integer);
    }
}
