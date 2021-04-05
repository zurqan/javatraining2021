package session1;

public class OperationsInJava {

    public static void main(String[] args) {


        //Add (+)
        int add = 10 +20;//+
        System.out.println("add = " + add);
        int a =10;
        int b =20;
        int add2 = a +b;
        System.out.println("add2 = " + add2);

        //sub (-)
        int sub= a-b;
        System.out.println("sub = " + sub);

        //mult (*)
        int mult = a*b;
        System.out.println("mult = " + mult);

        //div (/)
        int div = b/a;
        System.out.println("div = " + div);

        //mod (%)
        int mod = a%b;
        System.out.println("mod = " + mod);

        //byte,short,int,long,

        System.out.println("Math.abs(-10) = " + Math.abs(-10));


        int i = 20;
//        i = i+1;
        i+=1;
        System.out.println("i = " + i);
        i-=1;
        System.out.println("i = " + i);
        i*=3;
        System.out.println("i = " + i);

        i = 50;
        i++;//i+=1; i = i+1

        System.out.println("i = " + i);
        i--;//i-=1;i=i-1;
        System.out.println("i = " + i);

        boolean eq = i == 51;//!=, >, < ,>=,<=
        System.out.println("eq = " + eq);

        int number = 5;//101 -> 10
        number = number >>1;
        System.out.println("number = " + number);
        number = number>>2;
        System.out.println("number = " + number);

        int number1= 5 | 2;//101 or 010->111
        System.out.println("number1 = " + number1);
        number1= 5&2;
        System.out.println("number1 = " + number1);

        int d = (number1 < 0)
                    ? 100
                    : -100;

        System.out.println("d = " + d);

        boolean comp = (d>100) && (number>5);//||




    }
}
