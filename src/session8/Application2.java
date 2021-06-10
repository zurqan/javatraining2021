package session8;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application2 {

    public static void main(String[] args) {


        Stream
                .of(1, 2, 3, 4)
                .flatMap(n -> IntStream.rangeClosed(0, n).boxed())
                .forEach(System.out::println);
        ;// Integer -> Function <Integer or Function< ? super Integer>
        //.map()//our put R .. passed function should return R or ? extends R

    }
}
