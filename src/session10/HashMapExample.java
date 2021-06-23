package session10;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class HashMapExample {


    public static void main(String[] args) {
        Map<Integer,String> map= new HashMap<>();

        map.put(1,"One");
        map.put(2,"Two");
        map.put(3,"Three");

        System.out.println("map.get(1) = " + map.get(1));

        System.out.println("map.get(10) = " + map.get(10));

        System.out.println("map.getOrDefault(10,\"A Number\") = " + map.getOrDefault(10, "A Number"));

        System.out.println("map.get(10) = " + map.get(10));

        map.computeIfAbsent(1,k->"new One");
        System.out.println("map.get(1) = " + map.get(1));
        map.computeIfAbsent(10,k->"Ten");
        System.out.println("map.get(10) = " + map.get(10));


        System.out.println("map.get(10) = " + map.get(10));
        map.compute(10,(k,v)->{
            return "New "+v;
        });
        map.compute(100, getIntegerStringStringBiFunction());
        map.compute(1, getIntegerStringStringBiFunction());

        System.out.println("map.get(100) = " + map.get(100));
        System.out.println("map.get(1) = " + map.get(1));


    }

    private static BiFunction<Integer, String, String> getIntegerStringStringBiFunction() {
        return (k,v)->{
            System.out.println("v = " + v);
            return v==null?"Number":"New "+v;
        };
    }
}
