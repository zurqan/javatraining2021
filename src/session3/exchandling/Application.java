package session3.exchandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Application {

    public static void main(String[] args)  {

        MyMath myMath = new MyMath();
        int divide = myMath.divide1(20, 2);
        System.out.println("divide = " + divide);

        System.out.println("myMath.divide(4,0) = " + myMath.divide1(4, 65));


        callDivideWithCheckedException(myMath);
        System.out.println("divide = " + divide);
        closingResources();
        closingResourcesJava7();

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

    public static void closingResources(){

        PrintWriter writer =null;

        try {
            writer=new PrintWriter(new File("test.txt"));
            writer.println("Hello, World!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(writer!=null){
                writer.close();
            }
        }

    }

    public static void closingResourcesJava7(){


        try (PrintWriter writer=new PrintWriter(new File("test2.txt"))){

            writer.println("Hello, World!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
