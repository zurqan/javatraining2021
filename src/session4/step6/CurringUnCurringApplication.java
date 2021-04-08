package session4.step6;

import session4.step2.BiFunc;
import session4.step2.Func;

public class CurringUnCurringApplication {


    public static void main(String[] args) {

        BiFunc<Integer,Integer,Integer> add = (a,b)->a+b;


        //I will convert same function to be a->b->a+b ... curring using add function
        Func<Integer,Func<Integer,Integer>> currying = a->b->add.apply(a,b);



        Func<Integer,Func<Integer,Integer>> addV2= a->b->a+b;

        //now convert the above function to un-currying format using addV2
        BiFunc<Integer,Integer,Integer> addUncurrying = (a,b)->addV2.apply(a).apply(b);

    }
}
