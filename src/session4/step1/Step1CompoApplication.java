package session4.step1;

public class Step1CompoApplication {

    public static void main(String[] args) {
        IntFunc g= x->x+1;
        IntFunc g2= new IntFunc() {
            @Override
            public int apply(int a) {
                return a+1;
            }
        };
//        g==g2

        IntFunc f= x->x*x;
        IntFunc r= x->x-10;

        System.out.println("g.apply(4) = " + g.apply(4));
        System.out.println("f.apply(5) = " + f.apply(5));

        System.out.println("f.apply(g.apply(4)) = " + f.apply(g.apply(4)));

        //return a-> apply(g.apply(a));
        IntFunc compos = f.compos(g);// f.comp(g) == x->f.apply(g.apply(x)))==(x+1)^2
        System.out.println("compos.apply(4) = " + compos.apply(4));


        System.out.println(f.compos(r).apply(20));
        //same like the above
        IntFunc compos1 = f.compos(r);
        System.out.println(compos1.apply(20));

        System.out.println("r.compos(f.compos(g)).apply(10) = " + r.compos(f.compos(g)).apply(10));


        System.out.println("f.andThen(g).apply(4) = " + f.andThen(g).apply(4));//(4)^2+1=17
        System.out.println("f.compos(g).apply(4) = " + f.compos(g).apply(4));//(4+1)^2=25



        StrFunc greeting=x->"Hello "+x;
        StrFunc warning= x->"Warning: "+x;
        StrFunc jsonMsg = x->"{\n\t\"msg\": \""+x+"\"\n}";

        String greetingAhmad = greeting.apply("Ahmad");
        System.out.println("greeting.apply(\"Ahmad\") = " + greetingAhmad);

        System.out.println("jsonMsg.apply(greetingAhmad) = \n" + jsonMsg.apply(greetingAhmad));

        StrFunc readyGreetingJsonMsg = jsonMsg.compos(greeting);
        System.out.println("readyGreetingJsonMsg.apply(\"Ahmad\") = \n" + readyGreetingJsonMsg.apply("Ahmad"));

        System.out.println("jsonMsg.compos(warning).apply(\"Your card is about to be expired \") = \n" + jsonMsg.compos(warning).apply("Your card is about to be expired "));

    }
}
