package session1;

public class StringSample {

    public static void main(String[] args) {
        String name = "Ahmad";
        System.out.println("name = " + name);

        // Hello!
        String hello = "Hello\u0021";// \u0021-->!

        System.out.println("hello = " + hello);
        System.out.println("hello.length() = " + hello.length());


        String subHello = hello.substring(0, 4);//length of the output string end-start
        System.out.println("subHello = " + subHello);
        System.out.println("hello = " + hello);

        System.out.println("hello.startsWith(\"H\") = " + hello.startsWith("H"));
        System.out.println("hello.startsWith(\"A\") = " + hello.startsWith("A"));
        System.out.println("hello.startsWith(\"h\") = " + hello.startsWith("h"));

        System.out.println("hello.charAt(1) = " + hello.charAt(1));
        //char <- 16bit

        String exceptionalString="Java\uD83D\uDc31";
        System.out.println("exceptionalString = " + exceptionalString);
        System.out.println("exceptionalString.length() = " + exceptionalString.length());
        int i=exceptionalString.offsetByCodePoints(0,4);
        int charValue = exceptionalString.codePointAt(i);
        System.out.println("charValue = " + charValue);
        long actualLength = exceptionalString.codePoints().count();
        System.out.println("actualLength = " + actualLength);




    }
}
