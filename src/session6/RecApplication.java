package session6;

import java.util.function.Supplier;

import static session6.RecApplication.TailCall.result;
import static session6.RecApplication.TailCall.suspend;

public class RecApplication {

    public static void main(String[] args) {
        System.out.println("add(10,20) = " + add(10, 20));

//        System.out.println("add(10,40000) = " + add(10, 40000));// TCE TCO


        TailCall<Integer> tailCall1 = addTail(5, 10);
        System.out.println("tailCall1.isSuspend() = " + tailCall1.isSuspend());


        TailCall<Integer> tail2 = addTail(5, 0);
        System.out.println("tail2.isSuspend() = " + tail2.isSuspend());
        System.out.println("tail2.eval() = " + tail2.eval());

        System.out.println("tailCall1.eval() = " + tailCall1.eval());


        TailCall<Integer> tail3 = addTail(5, 4000000);
        System.out.println("tail3.eval() = " + tail3.eval());


        int value = addTCE(10, 500000);
        System.out.println("value = " + value);


//        long sum = sum(40000, 0);
//        System.out.println("sum = " + sum);

        System.out.println("sumTCE(40000, 0).eval() = " + sumTCE(40000, 0).eval());
    }


    public static long sum(long x,long result){

        return x==0
                ?result
                :sum(x-1,result+x);

    }

    public static TailCall<Long> sumTCE(long x,long result){

        return x==0
                ?result(result)
                :suspend(()->sumTCE(x-1,result+x));

    }

    public static int add(int x, int y){
//        return x+y;
        return y ==0
                ?x //-> x -> result(x)
                :add(x+1,y-1); // -> suspend(()->add(..)
    }



    public static int addTCE(int x,int y){
        TailCall<Integer> tce = addTail(x, y);
        return tce.eval();
    }

    private static TailCall<Integer> addTail(int x,int y){

        return y==0
                ? result(x)
                : suspend(()->addTail(x+1,y-1));
    }


    public static abstract class TailCall<R>{

        public abstract R eval();

        public abstract TailCall<R> resume();
        public abstract boolean isSuspend();

        public static <R> Result<R> result(R res){
            return new Result<>(res);
        }

        public static <R> Suspend<R> suspend(Supplier<TailCall<R>> suspendFunction){
            return new Suspend<>(suspendFunction);
        }

        public static class Result<R> extends TailCall<R>{

            private final R result;

            public Result(R result) {
                this.result = result;
            }

            @Override
            public R eval() {
                return result;
            }

            @Override
            public TailCall<R> resume() {
                throw new IllegalStateException("This is not a suspend Type");
            }

            @Override
            public boolean isSuspend() {
                return false;
            }
        }


        public static class Suspend<R> extends TailCall<R>{

            private final Supplier<TailCall<R>> resumeFunction;

            public Suspend(Supplier<TailCall<R>> resumeFunction) {
                this.resumeFunction = resumeFunction;
            }

            @Override
            public R eval() {
                TailCall<R> tailCall = resumeFunction.get();
                while (tailCall.isSuspend()){
                    tailCall=tailCall.resume();
                }
                return tailCall.eval();

//                throw new IllegalStateException(" I don't have the final result");
            }

            @Override
            public TailCall<R> resume() {
                return resumeFunction.get();
            }

            @Override
            public boolean isSuspend() {
                return true;
            }
        }
    }
}
