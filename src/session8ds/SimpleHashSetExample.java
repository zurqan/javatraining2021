package session8ds;

import java.util.HashSet;
import java.util.Set;

public class SimpleHashSetExample {

    public static void main(String[] args) {

//        ArrayList<Integer> integers = new ArrayList<>();
//        integers.add(1);
//        integers.add(1);
//        integers.add(1);
//        integers.add(1);

        MyArrayList<Integer> integers2 = new MyArrayList<>();
        integers2.addLast(1);
        integers2.addLast(1);
        integers2.addLast(1);
        integers2.addLast(1);


        System.out.println("integers2.contains(2) = " + integers2.contains(2));

        System.out.println("integers = " + integers2);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(1);
        System.out.println("set = " + set);
    }
}
