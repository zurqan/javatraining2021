package session7;

import java.util.stream.Stream;

public class RecessiveGenericExample {

    public static void main(String[] a){

        Stream
                .of(10,20,30)
                .map(b->b+1)
                .parallel()// if parallel return BaseStream
                .map(c->c+1)
                .forEach(System.out::println);
        ;


    }
}
