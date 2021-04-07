package session3.exchandling;

public class MyMath {


    public int divide(int a,int b) throws DivideCheckedException {
        if(b==0){
//            throw new IllegalArgumentException("b value should not be zero");
            throw new DivideCheckedException("b value should not be zero");
        }
        return a / b;
    }

    public int divide1(int a,int b)  {

        return a / b;
    }


    public int divide2(int a,int b){
        if(b==0){
            throw new DivideRunTimeException("B should not be zero");
        }

        return a / b;
    }
}
