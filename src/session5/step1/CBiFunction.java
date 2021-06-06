package session5.step1;

import java.util.function.Function;

public interface CBiFunction<T,U,V> extends Function<T,Function<U,V>> {
}
