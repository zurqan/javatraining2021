package session8ds;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {

    public static void main(String[] args) {
        Queue<Integer> queue=new LinkedList<>();

        queue.add(10);
        queue.add(20);
        queue.add(30);
        System.out.println("queue = " + queue);
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println("queue.peek() = " + queue.peek());

        System.out.println("queue.poll() = " + queue.poll());
//        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.remove() = " + queue.remove());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());

//        System.out.println("queue.remove() = " + queue.remove());//will throw exception


    }
}
