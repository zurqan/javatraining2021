package session5.step3;

import session5.step1.CBiFunction;

import java.util.ArrayList;
import java.util.function.Function;

public class ReducerApplication {


    //acc->elem->acc
    public static <E, A> A reduceL(CBiFunction<A, E, A> accumulateFunction, A initial, E... array) {
        A result = initial;
        if (array == null) {
            return result;
        }

        for (int i = 0; i < array.length; i++) {
            E element = array[i];
            result = accumulateFunction.apply(result).apply(element);
        }

        return result;
    }

    //elem->acc->acc
    public static <E, A> A reduceR(CBiFunction<E, A, A> accumulateFunction, A initial, E... array) {
        A result = initial;
        if (array == null) {
            return result;
        }

        for (int i = array.length - 1; i >= 0; i--) {
            E element = array[i];
            result = accumulateFunction.apply(element).apply(result);

        }

        return result;
    }

    public static void main(String[] args) {
        Integer sum = reduceL(
                acc -> elem -> acc + elem,
                0,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("sum = " + sum);


        String simulateSum = reduceL(
                acc -> elem -> "(" + acc + "+" + elem + ")",
                "0",
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("simulateSum = " + simulateSum);

        ArrayList<Integer> toList = reduceL(acc -> elem ->
        {
            acc.add(elem);//this is not pure
            return acc;
        }, new ArrayList<Integer>(), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("toList = " + toList);



        Integer sumR = reduceR(
                elem -> acc -> acc + elem,
                0,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("sumR = " + sumR);


        String simulateSumR = reduceR(
                elem -> acc -> "(" + elem + "+" + acc + ")",
                "0",
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("simulateSumR = " + simulateSumR);

        CBiFunction<Integer,Integer,Integer> sumInt= a->b->a+b;
        CBiFunction<Integer,Integer,Integer> intProduct= a->b->a*b;
        int s=ReducerApplication.<Integer,Integer>advanceReduceL()
                .apply(sumInt)
                .apply(0)
                .apply(new Integer[]{1,2,3,4,5});


        Function<Integer[], Integer> sumOfArray = ReducerApplication.<Integer, Integer>advanceReduceL()
                .apply(sumInt)
                .apply(0);

        Function<Integer[], Integer> productArray = ReducerApplication.<Integer, Integer>advanceReduceL()
                .apply(intProduct)
                .apply(1);


        Integer sum1 = sumOfArray.apply(new Integer[]{1, 2, 3, 4, 5, 6});
        System.out.println("sum1 = " + sum1);

        Integer product = productArray.apply(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("product = " + product);

    }

    //(acc->elem->acc)->initialValue->array->netAccumulated
    //acc:A
    //elem:T
    //(a->t->a)->a->T[]->a

    public static <A,T> Function<CBiFunction<A,T,A>,Function<A,Function<T[],A>>> advanceReduceL(){

        return combinFunction->initial->array->{
            if(array==null){
                return initial;
            }
            A result = initial;
            for (T t : array) {
                result = combinFunction.apply(result).apply(t);
            }
            return result;
        };
    }
}
