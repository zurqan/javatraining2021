package session1;

public class StringOperations {

    public static void main(String[] args) {

        String hello = "Hello,";//String is immutable
        String helloWorld = hello+"World";
        System.out.println("helloWorld = " + helloWorld);
        System.out.println(helloWorld+"!");

        StringBuilder builder = new StringBuilder("Hello,");//mutable
        builder.append("World").append("!");
        System.out.println("builder = " + builder);
//        StringBuffer -> Thread safe
    }
}
