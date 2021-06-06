package session5;

import java.util.function.Function;

public interface CBiFunction<T,U,V> extends Function<T,Function<U,V>> {
}
