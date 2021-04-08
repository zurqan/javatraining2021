package session4.step2;

public class Step2CompoApplication {

    public static void main(String[] args) {

        Func<Integer/*D*/,Integer/*T*/> add5= x->x+5;//g
        Func<Integer/*T*/,Integer/*U*/> sub10 = x->x-10;//f
        Func<Integer/*D*/, Integer/*U*/> compos = sub10.compos(add5);//f o g
        System.out.println("compos.apply(4) = " + compos.apply(4));

        Func<String,String> greeting=x->"Hello "+x;
        Func<String,Integer> strLength=x->x.length();
        Func<String, Integer> compos1 = strLength.compos(greeting);
        System.out.println("compos1.apply(\"Mohammad\") = " + compos1.apply("Mohammad"));

//        greeting.compos(strLength) // type not compatible
    }
}
