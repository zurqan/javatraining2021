package session5.step1;

public class MoreAboutFunctionalStep2 {

    public static void main(String[] args) {
        MyPredicate<Integer> isEven=a->a%2==0;
        System.out.println("isEven.test(10) = " + isEven.test(10));
        System.out.println("isEven.test(3) = " + isEven.test(3));

        MyPredicate<Integer> isGraterThan10 = a->a>10;

        System.out.println("isGraterThan10.test(13) = " + isGraterThan10.test(13));

        MyPredicate<Integer> isEvenAndGT10 = isEven.and(isGraterThan10);
        System.out.println("isEvenAndGT10.test(13) = " + isEvenAndGT10.test(13));

        MyPredicate<Integer> isOdd = isEven.neg();
        System.out.println("isOdd.test(3) = " + isOdd.test(3));
    }
}
