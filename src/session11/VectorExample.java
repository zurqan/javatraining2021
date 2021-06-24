package session11;

import java.util.Vector;

public class VectorExample {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();

        vector.add(1);
        vector.add(2);

        new Thread(()->{

            int size = vector.size();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("vector.get(vector.size()-1) = " + vector.get(size - 1));
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vector.remove(0);
            vector.remove(0);
        }).start();



    }
}
