package session2.lambda;

@FunctionalInterface
public interface IntFunction {

    public int apply(int a);
    default String resultAsString(int a){
        return "The result is: "+apply(a);
    }
}
