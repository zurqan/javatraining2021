package session4.step2;

@FunctionalInterface
public interface BiFunc<T,U,V> {

    public V apply(T t,U u);
}
