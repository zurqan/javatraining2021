package session11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class PublicationObject {

    private List<String> names = new ArrayList<>();
//    private List<String> names = new CopyOnWriteArrayList<>();



//    public List<String> getNames() {
//        return new ArrayList<>(names);
//    }
    public List<String> getNames() {
        return names;//Not Thread safe ..other will control same ref
    }
//
//    public void setNames(List<String> names) {
//        this.names = names;
//    }

    public synchronized String addAndGetLast(String name) {
        names.add(name);
        return names.get(names.size() - 1);
    }

    public static void main(String[] args) throws InterruptedException {
        PublicationObject obj = new PublicationObject( );
        int numberOfThreads = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads*2);
        for (int i = 0; i < numberOfThreads; i++) {
            int a = i;
            new Thread(()->{
                String lastName = obj.addAndGetLast("" + a);
                if(!lastName.equals(""+a)){
                    System.out.println("Strange!!");
                }
                countDownLatch.countDown();
            }).start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(()->{
                obj.getNames().replaceAll(a->"a");

                System.out.println("obj = " + obj.getNames().toString());
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();

    }
}
