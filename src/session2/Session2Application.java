package session2;

public class Session2Application {

    public static void main(String[] args) {
        SayHello o1 = new SayHello();
        SayHello o2 = new SayHello();

        String helloAhmad1 = o1.sayHello("Ahmad");
        String helloAhmad2 = o2.sayHello("Ahmad");

        System.out.println("Msg1 = " + helloAhmad1);
        System.out.println("Msg2 = " + helloAhmad2);
    }
}
