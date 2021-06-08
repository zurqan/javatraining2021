package session6;

import session5.step2.Gender;
import session5.step2.Student;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static session5.step2.Course.*;
import static session5.step2.Gender.FEMALE;
import static session5.step2.Gender.MALE;

public class ApplicationForCollect {
    static Student[] students = new Student[]{
            new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
            new Student("Mohammad", 31, MALE, true, asList(MATH, ARABIC)),
            new Student("Mosa", 20, MALE, false, asList(ENGLISH, ARABIC)),
            new Student("Heba", 20, FEMALE, true, asList(ENGLISH, CHEMISTRY)),
            new Student("Heba2", 20, FEMALE, true, asList(ENGLISH)),
            new Student("Ruba", 30, FEMALE, false, asList(JAVA, MATH, ENGLISH, CHEMISTRY)),
            new Student("Esa", 45, MALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY))

    };

    public static void main(String[] args) {

        IntSummaryStatistics statistics = Stream
                .of(students)
                .collect(Collectors.summarizingInt(st -> st.getCourses().size()));

        System.out.println("statistics.getAverage() = " + statistics.getAverage());
        System.out.println("statistics.getCount() = " + statistics.getCount());
        System.out.println("statistics.getMax() = " + statistics.getMax());
        System.out.println("statistics.getMin() = " + statistics.getMin());
        System.out.println("statistics.getSum() = " + statistics.getSum());

        IntSummaryStatistics ageSt = Stream
                .of(students)
                .collect(Collectors.summarizingInt(st -> st.getAge()));


        System.out.println("ageSt.getAverage() = " + ageSt.getAverage());
        System.out.println("ageSt.getCount() = " + ageSt.getCount());
        System.out.println("ageSt.getMax() = " + ageSt.getMax());
        System.out.println("ageSt.getMin() = " + ageSt.getMin());


        Map<Boolean, List<String>> activeAndInactiveGroup = Stream
                .of(students)
                .collect(partitioningBy(Student::isActive, mapping(Student::getName, toList())));

        System.out.println("activeAndInactiveGroup = " + activeAndInactiveGroup);


        Optional<Student> minStd = Stream
                .of(students)
                .collect(minBy(Comparator.comparing(Student::getAge)));

        System.out.println("minStd = " + minStd);


        Set<Gender> setOfStudent = Stream
                .of(students)
                .map(Student::getGender)
                .collect(new SetCollector<Gender>());


        System.out.println("setOfStudent = " + setOfStudent);


        String names = Stream
                .of(students)
                .map(Student::getName)
                .collect(joining(",","[","]"));
        System.out.println("names = " + names);


        Map<Gender, List<Student>> collect = Stream
                .of(students)
//                .reduce()
                .collect(groupingBy(Student::getGender));


    }


    //    public static class SetCollector<T> implements Collector<T,StringBuilder,String/*R result*/>{
    public static class SetCollector<T> implements Collector<T, List<T>/*A*/, Set<T>/*R result*/> {



        @Override
        public Supplier<List<T>> supplier() {
            return () -> new ArrayList<>();
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return (acc, e) -> acc.add(e);
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (acc1, acc2) -> {
                ArrayList<T> newAcc = new ArrayList<>(acc1);
                newAcc.addAll(acc2);
                return newAcc;
            };
        }

        @Override
        public Function<List<T>, Set<T>> finisher() {
            return acc-> new HashSet<>(acc);
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.singleton(Characteristics.UNORDERED);
        }
    }
}
