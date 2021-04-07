package session3;

public final class NamingConvention {

    private int index=0;

    private static int staticIndex=0;

    public static final double PI_VALUE=3.14;
    private final int finalIndex;

    private NamingConvention(int finalIndex) {
        this.finalIndex = finalIndex;
    }

    public final void methodName(){
       //when you extend class NamingConvention.. you will not be able to override this method
    }



    public static void staticMethodName(){
//        index++;
//        methodName();
        staticMethodName2();
        staticIndex++;


    }
    public static void staticMethodName2(){

    }

    public void increment(){
        staticIndex++;
        index++;
    }

    public int getIndex() {
        return index;
    }

    public static int getStaticIndex() {
        return staticIndex;
    }

    public void printStatic(){
        System.out.println("NamingConvention.printStatic : "+staticIndex);
    }

    public static void main(String[] args) {









        NamingConvention o1 = new NamingConvention(0);
        NamingConvention o2 = new NamingConvention(0);

        o1.increment();
        o1.increment();
        System.out.println("o1.getIndex() = " + o1.getIndex());
        o1.printStatic();
        System.out.println("o2.getIndex() = " + o2.getIndex());
        o2.printStatic();

        System.out.println("NamingConvention.getStaticIndex() = " + NamingConvention.getStaticIndex());
    }
}
