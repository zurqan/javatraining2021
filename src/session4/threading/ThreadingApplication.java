package session4.threading;

public class ThreadingApplication {


    public static void main(String[] args) throws InterruptedException {
//        //Main thread
//        throw  new RuntimeException();
        copyFile1();//->p1
        copyFile2();//->p1
        calculationOperation();//->p2

        copyFile3();


    }

    private static void calculationOperation() {
        System.out.println("Start Calculating");
        System.out.println("calculationOperation: Thread.currentThread().getName() = " + Thread.currentThread().getName());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end calculations");
    }

    private static void copyFile1() throws InterruptedException {


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Start copying - first way");
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("End copying");
                //throw new RuntimeException();
            }
        };
        thread1.start();

    }


    private static void copyFile2() throws InterruptedException {
        CopyFile copyFileThread = new CopyFile();
        copyFileThread.start();

    }

    private static class CopyFile extends Thread {
        @Override
        public void run() {
            System.out.println("Start copying - 2nd way");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("End copying");
//            throw new RuntimeException();
        }
    }

    private static void copyFile3() throws InterruptedException {
        Runnable copyFile=()->{
            System.out.println("Start copying - 3nd way");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("End copying");

        };

        Thread copyFileThread = new Thread(copyFile);
        copyFileThread.start();

    }

}
