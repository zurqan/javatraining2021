package session4.step3;

import java.util.Comparator;

public class Step3Application {

    public static void main(String[] args) {

        IntPredicate isEven=x->x%2==0;

        System.out.println("isEven.apply(10) = " + isEven.apply(10));
        System.out.println("isEven.apply(15) = " + isEven.apply(15));

        IntPredicate isGraterThan100=x->x>100;
        System.out.println("isGraterThan100.apply(20) = " + isGraterThan100.apply(20));
        System.out.println("isGraterThan100.apply(101) = " + isGraterThan100.apply(101));


        StrPredicate isEmpty=x->x.length()==0;
        StrPredicate isNull = x->x==null;
        StrPredicate startWithA = x->x.charAt(0)=='A';

        System.out.println("isEmpty.apply(\"Mo\") = " + isEmpty.apply("Mo"));
        System.out.println("isEmpty.apply(\"\") = " + isEmpty.apply(""));

        System.out.println("isNull.apply(null) = " + isNull.apply(null));

        System.out.println("startWithA.apply(\"Ahmad\") = " + startWithA.apply("Ahmad"));
        System.out.println("startWithA.apply(\"Mohammad\") = " + startWithA.apply("Mohammad"));


        Predicate<String> isEmptyBasheerVerison=x->x.length()==0;

        System.out.println("isEmptyBasheerVerison.apply(\"\") = " + isEmptyBasheerVerison.apply(""));

        Comparator<Integer> compare=(a,b)->Integer.compare(a,b);
//        Comparator<Integer> compare2=(a,b)->a>b?1:a<b?-1:0;

        System.out.println("compare.compare(1,20) = " + compare.compare(1, 20));
        System.out.println("compare.compare(20,1) = " + compare.compare(20, 1));
        System.out.println("compare.compare(2,2) = " + compare.compare(2, 2));



    }
}
