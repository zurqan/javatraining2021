package session11;

import java.util.concurrent.*;

public class AnotherFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(2);

//        FutureTask<Integer> future1 = new FutureTask<>(() -> {
//            System.out.println("Future1");
//            Thread.sleep(4000);
//            System.out.println("Future 1 done");
//
//            return 100;
//        });


//        FutureTask<String> future2 = new FutureTask<>(() -> {
//            System.out.println("Future2");
//            Thread.sleep(6000);
//            System.out.println("Future 2 done");
//            return "Hello";
//        });
//        FutureTask<Integer> future3 = new FutureTask<>(() -> {
//            System.out.println("Future3");
//            Thread.sleep(8000);
//            System.out.println("Future 3 done");
//
//            return 100;
//        });


//        FutureTask<String> future4 = new FutureTask<>(() -> {
//            System.out.println("Future4");
//            Thread.sleep(4000);
//            return "Hello";
//        });

//        executorService.submit(future1);
//        executorService.submit(future2);
//
//        executorService.submit(future3);
//        executorService.submit(future4);
//
//


//        while (!future1.isDone() || !future2.isDone()){
//            ;//
//        }

//        System.out.println("Done");
//        System.out.println("future2.get() = " + future2.get());
//        System.out.println("future1.get() = " + future1.get());
//
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test");
        },executorService);

        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> {
            System.out.println("test2");
        },executorService);

        CompletableFuture.allOf(c1,c2).get();
        System.out.println("Done from c1  and c2");


//        RXJava
        CompletableFuture
                .supplyAsync(()->"Test1")//A
                .thenCombineAsync(CompletableFuture.supplyAsync(()->"test2"),
                        (a1,a2)->a1+a2)//B
                .thenApplyAsync(str->"update1 "+str)
                .thenApply(str->"update "+str)

                .thenAccept(System.out::println);
        ;
        executorService.shutdown();


    }
}
