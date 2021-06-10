package session8;

@FunctionalInterface
public interface MyFunction<T,V> {

    public V apply(T t);//domain for current function T or any instance extends T

//    default  <W> MyFunction<W,V> compose(MyFunction<? super W, ? extends T> g){
//        //the domain for the final composed function W
//        return w->apply(g.apply(w));//co-domain for g is the domain for the current function which is(? extends T)
//    }

    default  <W> MyFunction<W,V> compose(MyFunction< ? super W, ? extends T> g /*before*/){
        //the domain for the final composed function W
        return w->apply(g.apply(w));//co-domain for g is the domain for the current function which is(? extends T)
    }

    default <W> MyFunction<T,W> andThen(MyFunction<? super V,? extends W> g /*after*/){
        return x->g.apply(apply(x));
    }
}
