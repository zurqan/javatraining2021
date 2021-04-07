package session3.exchandling;

public class Application {

    public static void main(String[] args)  {

        MyMath myMath = new MyMath();
        int divide = myMath.divide1(20, 2);
        System.out.println("divide = " + divide);

        System.out.println("myMath.divide(4,0) = " + myMath.divide1(4, 65));


        callDivideWithCheckedException(myMath);
        System.out.println("divide = " + divide);

    }

    private static void callDivideWithCheckedException(MyMath myMath)  {

        try {
            myMath.divide(10,0);
            System.out.println("Just for test");
        } catch (DivideCheckedException |ArithmeticException e) {
            e.printStackTrace();
            System.out.println("Exception thrown");
        }catch (Exception e){
            throw e;
        }
//        finally {
//            //
//        }
    }
}
