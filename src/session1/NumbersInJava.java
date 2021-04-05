package session1;

import java.math.BigInteger;

public class NumbersInJava {

    public static void main(String[] args) {

        byte b = -12;//range between -128 to 127
        System.out.println("b = " + b);

        short s = 10000;//-(2^15) to (2^15 -1)
        System.out.println("s = " + s);

        int i = 1000000; //-(2^31) to (2^31 -1) ->Integer
        System.out.println("i = " + i);

        long l = 1000000;//-(2^63) to (2^63 -1)
        System.out.println("l = " + l);

        float f =1.0f;
        System.out.println("f = " + f);

        int c =  i * i;
        System.out.println("c = " + c);

        double d = 100.100;

        System.out.println("d = " + d);

        BigInteger bi=new BigInteger("1000000");//check BigDecimal
        BigInteger updatedValue = bi.multiply(BigInteger.valueOf(2));
        System.out.println("bi = " + bi);
        System.out.println("updatedValue = " + updatedValue);

        BigInteger multiply = bi.multiply(bi).add(BigInteger.valueOf(1000).subtract(BigInteger.valueOf(1)));

        System.out.println("multiply = " + multiply);
        int i1 = multiply.intValue();
        System.out.println("i1 = " + i1);

        long bigL = l * l * l * l;
        System.out.println("bigL = " + bigL);



        int number = 0xFF;
        System.out.println("number = " + number);

        int number2 = 0b11;
        System.out.println("number2 = " + number2);
    }


}
