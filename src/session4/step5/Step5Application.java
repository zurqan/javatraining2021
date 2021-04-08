package session4.step5;

import java.util.ArrayList;
import java.util.List;

public class Step5Application {

    public static void main(String[] args) {

        MySupplier<String> greeting=()->"Hello";
        MySupplier<String> arGreeting=()->"مرحبا";


        String engGreeting = greeting.apply();
        System.out.println("greeting.apply() = " + greeting.apply());
        System.out.println("arGreeting.apply() = " + arGreeting.apply());

        MySupplier<List<String>> listGenerator=()->new ArrayList<>();
        List<String> strList = listGenerator.apply();
        System.out.println("strList = " + strList);

        //Supplier take no parameter


        //consumer return no value

        MyConsumer<String> print = a-> {
            System.out.println("Passed Parameter is : "+a);//note no return
        };


        print.apply("Mohammad");



        //Function a->b
        //BiFunction (a,b)->c
        //Predicate a->Boolean
        //Supplier ()->b
        //Consumer a->{}


    }
}
