package session8ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DeQueueExample {


    public static void main(String[] args) {

        Deque<Integer> deque = new LinkedList<>();

        deque.push(10);
        deque.push(20);
        deque.push(30);

        System.out.println("deque.getFirst() = " + deque.getFirst());
        System.out.println("deque.getLast() = " + deque.getLast());

//        System.out.println("deque.poll() = " + deque.poll());
//        System.out.println("deque.pollLast() = " + deque.pollLast());

        deque.removeIf(a->a>10);
        System.out.println("deque = " + deque);

        validate("((()))");
//        validate(")))");
//        validate("(((");
//        validate("((())))");
    }

    public static void validate(String str){

//        Deque<Character> chars=new LinkedList<>();
        Deque<Character> chars=new ArrayDeque<>();

        for (char c : str.toCharArray()) {

            if(c=='('){
                chars.push(c);
            }else{
                Character poll = chars.poll();
                if(poll ==null || poll !='('){
                    throw new RuntimeException("Not Valid");
                }
            }
        }

        if(!chars.isEmpty()){
            throw new RuntimeException("Not Valid");

        }
    }
}
