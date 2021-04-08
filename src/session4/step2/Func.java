package session4.step2;

public interface Func<T,U> {

    public U apply(T t);

    default <D> Func<D,U> compos(Func<D,T> g){

        return x->apply(g.apply(x));

    }
}
