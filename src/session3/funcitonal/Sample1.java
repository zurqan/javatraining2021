package session3.funcitonal;

import session3.sloid.ocp.User;

import java.util.List;

public class Sample1 {


    int i =5;
    final int x=5;
    static int y = 5;

    //pure
    public int add1(int a,int b){
        return a + b;
    }

    //pure
    public int add2(int a,int b){
        return a+b+5;
    }
    //not - pure
    public int add3(int a,int b){
        return a+b+i;
    }

    // pure
    public int add4(int a,int b){
        return a+b+x;
    }
    // not-pure
    public int add5(int a,int b){
        return a+b+y;
    }

    public static void setY(int y1){
        y=y1;
    }

    //pure
    public int add6(int a,int b){
        a=10;
        return a+b;
    }

    //not-pure
    public int add7(int a, int b, List<User> list){
        list.clear();
        return a+b;
    }

    //not-pure
    public int add8(int a,int b){
        System.out.println("a:" +a);
        return  a+b;
    }

    //not-pure
    public int divide(int a,int b){//such that b not equal to zero
        return a / b;
    }

    public static void main(String[] args) {
        Sample1 sample1 = new Sample1();
        System.out.println("sample1.add5(3,4) = " + sample1.add5(3, 4));

        Sample1.setY(10);
        System.out.println("sample1.add5(3,4) = " + sample1.add5(3, 4));

        int a=20;
        System.out.println("sample1.add6(a,13) = " + sample1.add6(a, 13));
        System.out.println("a = " + a);
    }
}
