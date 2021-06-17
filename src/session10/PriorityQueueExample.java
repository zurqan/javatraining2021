package session10;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample {

    public static void main(String[] args) {

        PriorityQueue<Integer> numberQueue=
                new PriorityQueue<>(Comparator.<Integer>comparingInt(a->a));

        numberQueue.add(10);
        numberQueue.add(1);
        numberQueue.add(100);
        numberQueue.add(20);

        System.out.println("numberQueue = " + numberQueue);

        while (numberQueue.size()>0){
            System.out.println("numberQueue.poll() = " + numberQueue.poll());
            System.out.println("numberQueue = " + numberQueue);
        }


        numberQueue=
                new PriorityQueue<>(Comparator.<Integer>comparingInt(a->a).reversed());

        numberQueue.add(10);
        numberQueue.add(1);
        numberQueue.add(100);
        numberQueue.add(20);

        System.out.println("numberQueue = " + numberQueue);


        PriorityQueue<Integer> largeFive = new PriorityQueue<>(6);

        addNumber(largeFive,5);
        addNumber(largeFive,0);
        addNumber(largeFive,12);
        addNumber(largeFive,50);
        addNumber(largeFive,4);
        addNumber(largeFive,3);
        addNumber(largeFive,1);
        addNumber(largeFive,20);

        //1,10,2,3,5 -> 1,2,3,5,10 -> 3
        //1,10,2,3 -> 1,2,3,10->2.5
    }

    private static void addNumber(PriorityQueue<Integer> largeFive, int i) {
        largeFive.add(i);
        if(largeFive.size()==6){
            largeFive.poll();
        }
        System.out.printf("queue after adding %d will be %s\n",i,largeFive);
    }
}
