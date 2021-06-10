package session8;

public class Application {

    public static void main(String[] args) {

//        MyFunction<String,Number> f = st->st.length();
//
//        MyFunction<Integer,String> g = n->""+n;

//        MyFunction<Integer, Number> compose = f.compose(g);//f(g(x))

//        g.compose(f)//compilation error

        MyFunction<B,String> f=null;

//        MyFunction<Number,B> g=null;//pass
//        MyFunction<Object,B> g=null;//pass
//        MyFunction<Number,C> g=null;//pass
        MyFunction<Object,C> g=null;//pass
//        MyFunction<Number,A> g=null;//not pass

        MyFunction<Number, String> compose = f.compose(g);
    }


    public static class A{
        public void aMethod(){
            System.out.println("A.aMethod");
        }
    }

    public static class B extends A{
        public void bMethod(){
            System.out.println("B.bMethod");
        }
    }

    public static class C extends B{

        public void cMethod(){
            System.out.println("C.cMethod");
        }
    }
}
