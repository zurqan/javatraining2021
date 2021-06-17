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

        PriorityQueue<Integer> highQueue = new PriorityQueue<>(Comparator.<Integer>comparingInt(a -> a));
        PriorityQueue<Integer> lowQueue = new PriorityQueue<>(Comparator.<Integer>comparingInt(a -> a).reversed());

        add(highQueue,lowQueue,10);//10  -> 10
        add(highQueue,lowQueue,4);//4 10 -> 7
        add(highQueue,lowQueue,5);//4 5 10 -> 5
        add(highQueue,lowQueue,12);//4 5 10 12 -> 7.5
        add(highQueue,lowQueue,3);//3 4 5 12 10 ->5
        add(highQueue,lowQueue,0);//0 3 4 5 10 12 ->4.5
        add(highQueue,lowQueue,0);//0 0 3 4 5 12 10 ->4
    }

    private static void add(PriorityQueue<Integer> highQueue, PriorityQueue<Integer> lowQueue, int number) {
        if(highQueue.size()==lowQueue.size()){
            if(highQueue.size()!=0 && lowQueue.peek()>number){
                highQueue.add(lowQueue.poll());
                lowQueue.add(number);
            }else {
                highQueue.add(number);
            }
        }else{
           highQueue.add(number);
           lowQueue.add(highQueue.poll());
        }

        double medianValue = median(highQueue,lowQueue);

        System.out.printf("high queue peek after adding %d is %d\n",number,highQueue.peek());
        System.out.printf("low queue peek after adding %d is %d\n",number,lowQueue.peek());
        System.out.printf("Median value is %s\n",medianValue);
    }

    private static double median(PriorityQueue<Integer> highQueue, PriorityQueue<Integer> lowQueue) {
        return highQueue.size()==lowQueue.size()
                ?(highQueue.peek()+lowQueue.peek())/2.0
                :highQueue.peek();
    }

    private static void addNumber(PriorityQueue<Integer> largeFive, int i) {
        largeFive.add(i);
        if(largeFive.size()==6){
            largeFive.poll();
        }
        System.out.printf("queue after adding %d will be %s\n",i,largeFive);
    }
}
