package session4.step2;

public class Step2Application {

    public static void main(String[] args) {
//        IntFunc add5= a->a+5;

        Func<Integer,Integer> add5 = a->a+5;

        System.out.println("add5.apply(10) = " + add5.apply(10));

        Func<Integer,Integer> inc=a->a+1;
        System.out.println("inc.apply(10) = " + inc.apply(10));

//        Func<Integer,Integer> divOnA= a-> {
//
//            try {
//                return 20 / a;
//            } catch (Exception e) {
////                e.printStackTrace();
//                return 0;
//            } finally {
//            }//not valid
//        };
//        System.out.println("divOnA.apply(2) = " + divOnA.apply(2));
//        System.out.println("divOnA.apply(0) = " + divOnA.apply(0));


        Func<String,String> helloGreeting= s-> String.format("Hello %s", s);
        System.out.println("helloGreeting.apply(\"World!\") = " + helloGreeting.apply("World!"));


        Func<String,Integer> strLength=a->a.length();
        System.out.println("strLength.apply(\"Mohammad\") = " + strLength.apply("Mohammad"));


        BiIntFunction add= (a,b)->a+b;
        System.out.println("add.apply(4,5) = " + add.apply(4, 5));

        BiIntFunction divide = (a,b)->a/b;

        System.out.println("divide.apply(10,2) = " + divide.apply(10, 2));


        BiSirFunction concatStr=(s1,s2)-> {
            System.out.println("First Parameter is: "+s1);
            System.out.println("Second Parameter is: "+s2);
            return s1 + s2;
        };
        System.out.println("concatStr.apply(\"Hello\",\" World!\") = " + concatStr.apply("Hello", " World!"));


        BiFunc<Integer,Integer,Integer> divide2=(a,b)->a/b;
        System.out.println("divide2.apply(10,2) = " + divide2.apply(10, 2));

        BiFunc<String,String,Integer> concatLength=(a,b)->(a+b).length();

        System.out.println("concatLength.apply(\"Mohammad\",\"Mohammad\") = " + concatLength.apply("Mohammad", "Mohammad"));

        BiFunc<String,String,String> concatV2=(a,b)->a+b;

        System.out.println("concatV2.apply(\"Hello\",\" World!\") = " + concatV2.apply("Hello", " World!"));

        BiFunc<String,String,Boolean> isSubString=
                (sub,str)->str.indexOf(sub) > -1;

        System.out.println("isSubString.apply(\"ham\",\"Mohammad\") = " + isSubString.apply("ham", "Mohammad"));

        BiFunc<Character,String,Boolean> startsWith=
                (c,str)->str.charAt(0)==c;

        System.out.println("startsWith.apply('M',\"Mohammad\") = " + startsWith.apply('M', "Mohammad"));
        System.out.println("startsWith.apply('A',\"Mohammad\") = " + startsWith.apply('A', "Mohammad"));

    }
}
