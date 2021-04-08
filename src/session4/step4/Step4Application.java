package session4.step4;

import session4.step2.Func;

public class Step4Application {


    public static void main(String[] args) {

        Func<Integer/*a*/,Func<Integer/*b*/,Integer/*a+b*/>>
                add= a->b->a+b;

        Func<Integer, Integer> f1 = add.apply(10);
        Integer result = f1.apply(20);
        System.out.println("result = " + result);

        System.out.println("add.apply(20).apply(15) = " + add.apply(20).apply(15));
    }
}
