package session1;

public class StringComp {

    public static void main(String[] args) {
        String hello = "Hello";
        String anotherHello = new String("Hello");
        String sameRefHello = "Hello";
        boolean sameReference = hello == anotherHello;
        System.out.println("sameReference ? " + sameReference);
        boolean sameRef2 = hello == sameRefHello;
        System.out.println("sameRef2 = " + sameRef2);

        ///right way to check eq
        boolean equals = hello.equals(anotherHello);
        System.out.println("equals check using equals = " + equals);

//        String h=null ;//="";
        String h="";

        System.out.println(h.equals(hello));

        String substring = hello.substring(0, 2);
        String anotherSubString = "He";
        String anotherAnotherString="He";

        System.out.println("hello = " + hello);
        System.out.println("substring = " + substring);
        System.out.println("anotherSubString = " + anotherSubString);
        System.out.println("anotherAnotherString = " + anotherAnotherString);

        boolean check = substring == anotherSubString;
        System.out.println("check = " + check);

        boolean anotherCheck = anotherSubString == anotherAnotherString;
        System.out.println("anotherCheck = " + anotherCheck);

    }
}
