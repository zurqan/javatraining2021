package session7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WhyGeneric {


    public static class A{

    }
    public static class B extends A{

    }

    public static class B2 extends A{

    }

    public static class C extends B{

    }

    public static class D extends C{

    }
    public static void printAfterAdding5(List numbers){
        for (Object number : numbers) {
//            if(number instanceof Integer)
            int a =(Integer)number;
            a+=5;
            System.out.println("a = " + a);

        }
    }

    public static void printListElementOfIntegers(List<Integer> p0){
        for (Integer a : p0) {
            System.out.println("a = " + a);
        }
    }

    public static void printNumber( Number a){
        System.out.println("a = " + a);
    }


    public static void printNumbers(List<Number> a){
        a.add(5);
        a.add(5.5);
        for (Number number : a) {

            System.out.println("number = " + number);
        }
    }
    public static void printNumbersV2(List<? extends Number> a){
//        a.add(5);I can not add integer because I am not sure if a is a List<Integer>
//        a.add(5.5); I can not add double because I am not sure if a is a List<Double>

        //But I can Read from List because I am sure that element of e will be a Number
//        Number number1 = a.get(0);
        for (Number number : a) {

            System.out.println("number int = " + number.intValue());
        }
    }

    public static void add5ToAList(List<? super Integer> numbers){
        numbers.add(5);
    }
    public static void addAToAList(List<? super A> instancesOfA){
        instancesOfA.add(new A());
    }

    public static void printAfterAdding5V2(List<Integer> numbers){
        for (Integer number : numbers) {
            number+=5;
            System.out.println("a = " + number);

        }
    }

    private static void printListElementOfStrings(List<String> p0) {
        for (String a : p0) {
            System.out.println("a = " + a);
        }
    }
    private static void printListElement(List<Object> p0) {
        for (Object a : p0) {
            System.out.println("a = " + a);
        }
    }

    private static void printListElementGeneric(List<? extends Object> p0) {
        for (Object a : p0) {
            System.out.println("a = " + a);
        }
    }


    public static void main(String[] args) {
//        List numbers = Arrays.asList(1,2,3,4);//pass
//        List numbers = Arrays.asList(1,2,3,4,"a");

//        printAfterAdding5(numbers);

        List<Integer> numbers2 = Arrays.asList(1,2,3,4);
        List<Double> doubles = Arrays.asList(1.2, 3.3, 4.4);
        List<String> names = Arrays.asList("Ahmad","Mohammad","Esa","Mahmood");
//        printAfterAdding5V2(numbers2);

//        List<String> strings = Arrays.asList("1", "2", "3");
//        printAfterAdding5V2(strings);

//        printNumber(10);
//        printNumber(10.1);

//        printNumbers(numbers2);//compilation error

        printListElementOfIntegers(numbers2);
        printListElementOfStrings(names);

//        printListElement(numbers2);//compilation error
//        printListElement(names);//compilation error

        printListElementGeneric(numbers2);
        printListElementGeneric(doubles);
        printListElementGeneric(names);

        printNumbersV2(doubles);
//        printNumbersV2(names);//compilation error String is not extending Number


//        List<Object> objList = new ArrayList(){{
//            add(1);
//            add(2);
//            add(3);
//            add("Ahmad");
//        }};
        //Arrays.asList(1, 2, 3, "Ahmad");
        List<Object> objList =new ArrayList<>(Arrays.asList(1, 2, 3, "Ahmad"));
        objList.add(5);

        add5ToAList(new ArrayList<Object>());
        addAToAList(new ArrayList<>());
//        add5ToAList(names); compilation error


        List<A> a = new ArrayList<>();
        List<B> b = new ArrayList<>();

        a.add(new B());
        b.add(new B());
        objList.add(new B());

        List<C> c=new ArrayList<>();
        List<D> d = new ArrayList<>();
        List<B2> b2 = new ArrayList<>();

//        readB(c);
//        readB(d);
//        readB(b2); // compilation error

        //PECS



        Stream
                .of(WhyGeneric.class.getMethods())
                .filter(m -> m.getName().contains("printListElementOfIntegers"))
                .forEach(System.out::println);

    }


    public static void addB(List<? super B> b){

        b.add(new B());
    }
    public static void readB(List<? extends B> b){

        B o = b.get(0);
    }
}
