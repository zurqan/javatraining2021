package session5.step1;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MoreAboutFunctional {

    public static <T,U,V> Function<T,Function<U,V>> convertToCurrying(BiFunction<T,U,V> biFunction){
        // (a,b)->operation ===> a->b->operation

        return a->b->biFunction.apply(a,b);

    }

    //CBiFunction===Function<T,Function<U,V>>
    public static <T,U,V> CBiFunction<T,U,V> convertToCurryingV2(BiFunction<T,U,V> biFunction){
        // (a,b)->operation ===> a->b->operation

        return a->b->biFunction.apply(a,b);

    }

    public static void main(String[] args) {

        //a->a+1
        Function<Integer, Integer> inc = a -> a + 1;
        System.out.println("inc.apply(5) = " + inc.apply(5));

        //(a,b)->a+b
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("add.apply(2,3) = " + add.apply(2, 3));

        Function<Integer, Function<Integer, Integer>> addWithCurrying = convertToCurrying(add);
        System.out.println("addWithCurrying.apply(2).apply(3) = " + addWithCurrying.apply(2).apply(3));

        //a->(b->a+b)
        //when a==2 -> result b->2+b

        //step 1
//        Function<Integer,Function<Integer,Integer>> addV2=a->b->a+b;
        //step 2
//        CBiFunction<Integer,Integer,Integer> addV2=a->b->a+b;
        //step 3
        BiIntFunc addV2=a->b->a+b;

        Function<Integer, Integer> add2 = addV2.apply(2);

        System.out.println("add2.apply(3) = " + add2.apply(3));
        System.out.println("add2.apply(6) = " + add2.apply(6));



        // a->(b->c->a+b+c)
        //(b->c->a+b+c)
        //(c->a+b+c)
        Function<Integer,Function<Integer,Function<Integer,Integer>>>
                add3= a->b->c->a+b+c;

        System.out.println("add3.apply(1).apply(2).apply(3) = " + add3.apply(1).apply(2).apply(3));

        Function<Integer, Function<Integer, Integer>> f1 = add3.apply(1);
        Function<Integer, Integer> f2 = f1.apply(2);
        Integer result = f2.apply(3);
        System.out.println("result = " + result);
    }
}
