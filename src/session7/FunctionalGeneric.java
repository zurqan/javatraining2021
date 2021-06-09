package session7;

import java.util.function.Function;
import java.util.stream.Stream;

public class FunctionalGeneric {

    public static void main(String[] args) {
        int result = mathOperation(1,
                x -> x + 1,
                x -> x * 2,
                x -> x * x,
                x -> x - 5);
        System.out.println(result);
        result = mathOperation2(1,
                x -> x + 1,
                x -> x * 2,
                x -> x * x,
                x -> x - 5);
        System.out.println(result);
        int result2 = mathOperation(3,
                x -> x + 1,
                x -> x * 2,
                x -> x * x,
                x -> x - 5);
        System.out.println(result2);
    }


    @SafeVarargs
    public static int mathOperation(int seed,
                                    Function<Integer,Integer>... functions){

//        Object[] f1 = functions;
//        Supplier<Integer> a = ()->2;
//        f1[0]=a;

        Function<Integer, Integer> reduce = Stream
                .of(functions)
                .reduce(x -> x,
                        (acc, e) -> e.compose(acc));

        return reduce.apply(seed);


    }

    public static int mathOperation2(int seed,
                                    Function<Integer,Integer>... functions){

        Function<Integer, Integer> reduce = Stream
                .of(functions)
                .reduce(x -> x,
                        (acc, e) -> acc.compose(e));

        return reduce.apply(seed);


    }
}
