package session5.step3;

import java.util.stream.IntStream;

public class Application4 {

    public static void main(String[] args) {

        String simulateSum = IntStream
                .rangeClosed(1, 10)//1,2,3,4,5,6,7,8,9,10
                .boxed()
//                .parallel()
                .reduce("0",//initial value
                        (acc, e) -> "(" + acc + "+" + e + ")", //acc function
                        (acc1, acc2) -> "(" + acc1 + "+" + acc2 + ")"//combiner
                );

        System.out.println(simulateSum);
    }
}
