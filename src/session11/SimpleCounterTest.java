package session11;

public class SimpleCounterTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {

            new Thread(()->SingletonObj.instance()).start();
        }
    }
}
