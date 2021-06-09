package session7;

import java.util.List;

public class WildExmaple {


    public static void main(String[] args) {

    }

    public static void dummyJob(List<?> l) {
        //op1
        //op2
        //op3

//        l.set(0, l.get(0));
        capture(l);

        // Don't use the following
//        List a = l;
//        a.set(0,"test");
//        a.set(0,a.get(0));
    }

    private static <T> void capture(List<T> l) {
        l.set(0, l.get(0));
//        l.set(0,"test");
    }

    public static void swapFirst(List<? extends Number> l1,
                                 List<? extends Number> l2){
        Number number = l1.get(0);
//        l1.set(0,l2.get(0));
//
//        l2.set(0,number);

        //Don't do this
//        List l1V2 = l1;
//        List l2V2 = l2;
//        Object temp = l1V2.get(0);
//        l1V2.set(0,l2V2.get(0));
//        l2V2.set(0, temp);
    }
}
