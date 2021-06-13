package test;

import session5.step1.CBiFunction;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Stream;

public class Util {

    //(a->b->a)->a->stream<b>->stream<a>
    public static <A, E> Function<CBiFunction<A, E, A>, CBiFunction<A, Stream<E>, Stream<A>>> scan() {
        AtomicInteger atomic = new AtomicInteger(0);

        return f -> init -> eStream -> {
            AtomicReference<A> ref = new AtomicReference<>(init);
            return Stream.concat(Stream.of(init),
                    eStream.map(e -> {
                        A newAcc = f.apply(ref.get()).apply(e);
                        ref.set(newAcc);
                        return newAcc;
                    }));
        };
    }

    public static Stream<BigInteger> fib(int limit) {

        return limit == 0
                ? Stream.empty()
                : Stream
                .concat(Stream.of(BigInteger.valueOf(1l)), Util.<BigInteger, BigInteger>scan().apply(a -> b -> a.add(b)).apply(BigInteger.valueOf(1l)).apply(fib(limit - 1)));
    }


    public static void main(String[] args) {
//        Stream<Integer> s = Util.<Integer, Integer>scan().apply(acc -> e -> acc + e).apply(1).apply(Stream.iterate(10, a -> a + 10));

//        s.limit(10).forEach(System.out::println);

        fib(1001)
                .skip(2000)
                .forEach(System.out::println);

    }

}
