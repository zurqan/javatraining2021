package session5.step3;

import session5.step2.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static session5.step2.Course.*;
import static session5.step2.Gender.FEMALE;
import static session5.step2.Gender.MALE;

public class Application3 {
    static Student[] students = new Student[]{
            new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
            new Student("Mohammad", 31, MALE, true, asList(MATH, ARABIC)),
            new Student("Mosa", 20, MALE, false, asList(ENGLISH, ARABIC)),
            new Student("Heba", 20, FEMALE, true, asList(ENGLISH, CHEMISTRY)),
            new Student("Ruba", 30, FEMALE, false, asList(JAVA, MATH, ENGLISH, CHEMISTRY)),
            new Student("Esa", 45, MALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY))

    };

    static BiFunction<ArrayList<Student>, Student, ArrayList<Student>> append = (acc, e) -> {
//                            acc.add(e);//it is not a pure function
        // return acc;

        ArrayList<Student> newList = new ArrayList<>(acc);
        newList.add(e);
        return newList;
    };


    public static void main(String[] args) {

        int sum = IntStream
                .rangeClosed(1, 10)
                .sum();
        System.out.println("sum = " + sum);

        int reduce = IntStream
                .rangeClosed(1, 10)
                .reduce(0, (acc, e) -> acc + e);
        System.out.println("reduce = " + reduce);

        int mult = IntStream
                .rangeClosed(1, 5)
                .reduce(1, (acc, e) -> acc * e);

        System.out.println("mult = " + mult);



        List<Student> listOfStudents = Stream
                .of(students)
                .reduce(
                        new ArrayList<Student>(),
                        append,
                        (acc1,acc2)-> {
                            System.out.println("combiner");
                            return acc1;
                        });


        List<Student> listOfStudentsParallel = Stream
                .of(students)

                .parallel()
                .reduce(
                        new ArrayList<Student>(),
                        append,

                        (acc1, acc2) -> {
                            System.out.println("combiner");

                            ArrayList<Student> students = new ArrayList<>(acc1);
                            students.addAll(acc2);
                            return students;
                        });


        System.out.println("listOfStudents = " + listOfStudents);

        OptionalInt optMax = IntStream
                .of(10, 2, 3, 4, 100, 40, 30)
                .reduce((acc, e) -> acc > e ? acc : e);

        System.out.println("optMax = " + optMax);

        OptionalInt optMin = IntStream
                .of(10, 2, 3, 4, 100, 40, 30)
                .reduce((acc, e) -> acc < e ? acc : e);
        System.out.println("optMin = " + optMin);

        int min = IntStream
                .of(10, 2, 3, 4, 100, 40, 30)
                .reduce((acc, e) -> acc < e ? acc : e)
                .orElseThrow(()->new RuntimeException("No Element Found!"));
//                .orElseGet(()->-1)
//                .orElse(-1);
        System.out.println("min = " + min);


        Stream
                .of(students)
                .parallel()
                .map(st-> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("st.getName() = " + st.getName());
                    return st.getName(); })
                .count();
    }
}
