package session4.step4;

import session4.step2.BiFunc;
import session4.step2.BiIntFunction;
import session4.step2.Func;

public class Step4Application {


    public static void main(String[] args) {


        BiFunc<Integer/*a*/,Integer/*b*/,Integer/*a+b*/>
                addUsingBi=(a,b)->a+b;


        BiIntFunction addUsingBiInt = (a,b)->a+b;


        //a->b->a+b
        //a->[[[[b->a+b]]]]<----this is a function
        Func<Integer/*a*/,Func<Integer/*b*/,Integer/*a+b*/>>
                add= a->b->a+b;

        Func<Integer, Integer> f1 = add.apply(10/*a*/);
        Integer result = f1.apply(20/*b*/);
        System.out.println("result = " + result);

        System.out.println("add.apply(20).apply(15) = " + add.apply(20).apply(15));



        //a->b->a*(100+b)/100 <---curring
        Func<Integer/*a*/,Func<Integer/*b*/,Integer/*a+b*/>>
                netPrice=tax->actualItemPrice->
                (actualItemPrice*(100+tax))/100;

        System.out.println("netPrice.apply(16).apply(100) = " + netPrice.apply(16).apply(100));
        System.out.println("netPrice.apply(16).apply(50) = " + netPrice.apply(16).apply(50));

        //netPrice=tax->actualItemPrice->
        //                (actualItemPrice*(100+tax))/100;

        //if tax == 16 in jordan
        //actualItemPrice->(actualItemPrice*(100+16))/100;
        Func<Integer, Integer> netPriceInJordan = netPrice.apply(16);//Partialy applied with 16

        //if tax == 2 in SA
        //actualItemPrice->(actualItemPrice*(100+2))/100;

        Func<Integer, Integer> netPriceInSA = netPrice.apply(2);//partialy applied with 2


        System.out.println("netPriceInJordan.apply(100) = " + netPriceInJordan.apply(100));
        System.out.println("netPriceInJordan.apply(50) = " + netPriceInJordan.apply(50));

        System.out.println("netPriceInSA.apply(100) = " + netPriceInSA.apply(100));
        System.out.println("netPriceInSA.apply(50) = " + netPriceInSA.apply(50));



        ////
        //a->b->c->a+b+c
        //when apply a0 .... result b->c->a0+b+c
        //when apply b0 after a0 ... result c->a0+b0+c
        Func<Integer,Func<Integer,Func<Integer,Integer>>> add3= a->b->c->a+b+c;

        Integer finalResult = add3.apply(10).apply(12).apply(20);
        System.out.println("finalResult = " + finalResult);

        //b->c->10+b+c
        Func<Integer, Func<Integer, Integer>> add3F1 = add3.apply(10);//Partial Applied Function
        //c->10+12+c
        Func<Integer, Integer> add3F2 = add3F1.apply(12);

        Integer finalResult2 = add3F2.apply(20);
        System.out.println("finalResult2 = " + finalResult2);






    }
}
