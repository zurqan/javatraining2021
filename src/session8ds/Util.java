package session8ds;

import session5.step1.CBiFunction;

import java.util.function.BiFunction;

public class Util {

    public static <T,U,V> CBiFunction<T,U,V> currying(BiFunction<T,U,V> function){
        return a->b->function.apply(a,b);
    }
}
