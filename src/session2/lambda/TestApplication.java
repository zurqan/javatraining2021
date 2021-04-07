package session2.lambda;

public class TestApplication {

    public static void main(String[] args) {
        IntFunction addFive= new AddFiveFunction();
        System.out.println("addFive = " + addFive.apply(10));

        IntFunction addFiveV2= new IntFunction() {
            @Override
            public int apply(int a) {
                return a+5;
            }
        };

        System.out.println("addFiveV2 = " + addFiveV2.apply(10));

        int five=5;
        IntFunction addFiveV3=a->a+five;
//        IntFunction addFiveV3=(int a)->a+5;
//        IntFunction addFiveV3=(int a)->{
//            return a+5;
//        };
        // a->b->c->a+b+c
        System.out.println("addFiveV3 = "+addFiveV3.apply(10));
    }
}
