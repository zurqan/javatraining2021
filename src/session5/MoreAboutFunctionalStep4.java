package session5;

import java.util.Arrays;

public class MoreAboutFunctionalStep4 {

    public static void main(String[] args) {
        //a->{}

        MyConsumer<Object> log= a->{
            System.out.println("a = " + a);
        };

        log.consume(10);

        log.consume(Arrays.asList(10,20,30));
    }
}
