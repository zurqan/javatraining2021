package session2;

public class LocalClassExample {
    private String name="UNKOWN";
    public void test(){
        int a=0;
        class MyLocalClass{
//            private static int b=10;
            public void print(){
                System.out.println("Hello World,"+name);
                System.out.println(a);
            }
        }
//        a++;
        MyLocalClass myLocalClass = new MyLocalClass();
        myLocalClass.print();

    }
}
