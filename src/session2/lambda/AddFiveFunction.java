package session2.lambda;

public class AddFiveFunction implements IntFunction {
    @Override
    public int apply(int a) {
        return a+5;
    }
}
