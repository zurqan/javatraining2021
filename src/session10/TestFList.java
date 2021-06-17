package session10;

import session6.Application.Tuple;

public class TestFList {

    public static void main(String[] args) {

        FList<Object> empty = FList.empty();
        System.out.println("empty = " + empty);

        FList<Integer> numbers = FList.of(1, 2, 3, 4,5,6,7,8,9,10);
        System.out.println("numbers = " + numbers);
//        System.out.println("numbers.getClass() = " + numbers.getClass());

        System.out.println("numbers.reverse() = " + numbers.reverse());
        System.out.println("numbers = " + numbers);


        System.out.println("empty.size() = " + empty.size());
        System.out.println("numbers.size() = " + numbers.size());
        System.out.println("numbers.reverse().size() = " + numbers.reverse().size());

        FList<Integer> subList = numbers.drop(5);
        System.out.println("subList = " + subList);
        System.out.println("subList.size() = " + subList.size());
        System.out.println("numbers = " + numbers);

        FList<Integer> dropUntilReach7OrGt = numbers.dropWhile(n -> n < 7);
        System.out.println("dropUntilReach7OrGt = " + dropUntilReach7OrGt);

        Integer sum = numbers.reduceL(0, a -> e -> a + e);
        System.out.println("sum = " + sum);

        FList<String> strNumb = numbers.map(e -> String.format("number is %d", e));
        System.out.println("strNumb = " + strNumb);

        FList<Integer> integerFList = numbers.flatMap(e -> FList.of(10 * e - 1, 10 * e, 10 * e + 1));
        System.out.println("integerFList.size() = " + integerFList.size());
        System.out.println("integerFList = " + integerFList);

        FList<String> numNames = FList.of("One", "Two", "Three");
        FList<String> zip = FList.zip(numbers, numNames, n -> name -> name + " - " + n);
        System.out.println("zip = " + zip);

        FList<Tuple<Integer, String>> tupleZip = FList.zip(numbers, numNames, n -> name -> new Tuple<>(n, name));
        System.out.println("tupleZip  = " + tupleZip);

        FList<Tuple<Integer, String>> tupleFList = FList.tupleZip(numbers, numNames);
        System.out.println("tupleZip2 = " + tupleFList);
        Tuple<FList<Integer>, FList<String>> fListFListTuple = FList.unZip(tupleFList);
        System.out.println("fListFListTuple = " + fListFListTuple);

    }
}
