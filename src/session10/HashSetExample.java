package session10;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class HashSetExample {

    public static void main(String[] args) {

        Set<Integer> numbers = new HashSet<>();

        numbers.add(10);
        numbers.add(100);
        numbers.add(3);
        numbers.add(30);

        System.out.println("numbers = " + numbers);

        numbers = new LinkedHashSet<>();

        numbers.add(10);
        numbers.add(100);
        numbers.add(3);
        numbers.add(30);

        System.out.println("numbers = " + numbers);


    }
}
