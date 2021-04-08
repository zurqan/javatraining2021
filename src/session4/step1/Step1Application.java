package session4.step1;

public class Step1Application {

    public static void main(String[] args) {
        IntFunc add5=a->a+5;
//        IntFunc add5V2=(int a)->{
//            return a+5;};

        System.out.println("add5.apply(6) = " + add5.apply(6));

        System.out.println("add5.apply(12) = " + add5.apply(12));

        IntFunc mult5 = a->a*5;

        System.out.println("mult5.apply(5) = " + mult5.apply(5));
        System.out.println("mult5.apply(30) = " + mult5.apply(30));


        StrFunc helloGreeting = a->"Hello,"+a;

        System.out.println("helloGreeting.apply(\"Owies\") = " + helloGreeting.apply("Owies"));

        System.out.println("helloGreeting.apply(\"World!\") = " + helloGreeting.apply("World!"));


        IntFunc inc=c->c+1;
        System.out.println("inc.apply(4) = " + inc.apply(4));
        System.out.println("inc.apply(inc.apply(4)) = " + inc.apply(inc.apply(4)));


        StrIntFunc strLength=s->s.length();
        System.out.println("strLength.apply(\"Mohammad\") = " + strLength.apply("Mohammad"));

        System.out.println("strLength.apply(\"Java😄\") = " + strLength.apply("Java😄"));


        System.out.println("getMeAdd10Function().apply(30) = " + getMeAdd10Function().apply(30));

    }


    public static IntFunc getMeAdd10Function(){

//        IntFunc temp=a->a+10;
//
//        return temp;

        return a->a+10;
    }
    public static IntFunc getMeAdd10Function2(){

        IntFunc temp=new IntFunc() {
            @Override
            public int apply(int a) {
                return a+10;
            }
        };
        return temp;
    }
}
