package session7;

public class BoundedParametersApplication {

    public static class IntegerBox{

        private final int a;

        public IntegerBox(int a) {
            this.a = a;
        }
    }
    public static class IntegerBoxV2 implements Comparable<IntegerBoxV2>{
        private final int a;

        public IntegerBoxV2(int a) {
            this.a = a;
        }

        @Override
        public int compareTo(IntegerBoxV2 another) {
            return Integer.compare(a,another.a);//null pointer exception if another is null
        }

        @Override
        public String toString() {
            return "IntegerBoxV2{" +
                    "a=" + a +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("max(10,20) = " + max(10, 20));
        System.out.println("max(30,20) = " + max(30, 20));
        System.out.println("max(\"a\",\"b\") = " + max("a", "b"));

//        max(new IntegerBox(10),new IntegerBox(20));//compilation error

        IntegerBoxV2 max = max(new IntegerBoxV2(10), new IntegerBoxV2(20));
        System.out.println("max = " + max);
        max = max(new IntegerBoxV2(30), new IntegerBoxV2(20));
        System.out.println("max = " + max);

    }
    public static <T extends  Comparable> T max(T a0, T a1) {
        return a0.compareTo(a1) > 0
                ? a0
                : a1;
    }

    public static <T extends Number & Comparable> T max2(T a0, T a1) {
        return a0.compareTo(a1) > 0
                ? a0
                : a1;
    }
}
