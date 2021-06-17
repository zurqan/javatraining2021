package session10;

import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetExample {

    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(1);
        numbers.add(5);
        numbers.add(6);
        numbers.add(2);
        numbers.add(20);
        numbers.add(3);

        System.out.println("numbers = " + numbers);

        SortedSet<Integer> subList = numbers.subSet(2, 10);
        System.out.println("subList = " + subList);


        System.out.println("numbers.headSet(4) = " + numbers.headSet(4));

        System.out.println("numbers.tailSet(4) = " + numbers.tailSet(4));

        System.out.println("numbers.ceiling(5) = " + numbers.ceiling(5));
        System.out.println("numbers.ceiling(5) = " + numbers.ceiling(0));

        System.out.println("numbers.ceiling(11) = " + numbers.ceiling(11));

        System.out.println("numbers.floor(100) = " + numbers.floor(100));
        System.out.println("numbers.floor(4) = " + numbers.floor(4));
        System.out.println("numbers.floor(0) = " + numbers.floor(0));
    }
}
