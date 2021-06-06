package session5.step2;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static session5.step2.Course.*;
import static session5.step2.Gender.FEMALE;
import static session5.step2.Gender.MALE;

public class Application {

    static Student[] students = new Student[]{
            new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
            new Student("Mohammad", 31, MALE, true, asList(MATH, ARABIC)),
            new Student("Mosa", 20, MALE, false, asList(ENGLISH, ARABIC)),
            new Student("Heba", 20, FEMALE, true, asList(ENGLISH, CHEMISTRY)),
            new Student("Ruba", 30, FEMALE, false, asList(JAVA, MATH, ENGLISH, CHEMISTRY)),
            new Student("Esa", 45, MALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY))

    };

    public static void main(String[] args) {

//        Stream<Student> students = Stream.of(Application.students);
        Stream
                .of(Application.students)
                .forEach(st -> {
                    System.out.println(st);
                });//a-{}
        Stream
                .of(Application.students)
                .forEach(System.out::println);//a-{}


        //========
        Stream.of(students)
                .map(st -> st.getName())
                .forEach(name -> System.out.println("name = " + name));

        //========
        System.out.println("print make student's name");
        Stream
                .of(students)
                .filter(st -> st.getGender() == MALE)//predicate
                .map(Student::getName)//function
                .forEach(System.out::println);//consumer

        //====
        System.out.println("Print Active Student");
        Stream
                .of(students)
                .filter(Student::isActive)
                .map(Student::getName)
                .forEach(System.out::println);

        Stream
                .of(students)
                .peek(System.out::println)
                .map(Student::getName)
                .forEach(System.out::println);


        System.out.println("Intermediate Operation");
        Stream
                .of(students)
                .map(Student::getName)
                .peek(System.out::println)
                .filter(name -> name.startsWith("A"))
                .peek(System.out::println);

        System.out.println("Intermediate Operation - with count");

        long count = Stream
                .of(students)
                .map(Student::getName)
                .peek(System.out::println)
                .filter(name -> name.startsWith("A"))
                .peek(System.out::println)
                .count();

        System.out.println("count = " + count);
        Stream<String> streamOfNames = Stream
                .of(students)
                .map(student -> {
                    System.out.println("Map to names");
                    return student.getName();
                });

        Stream<String> streamOfNamesStartsWithA = streamOfNames.filter(name -> name.startsWith("A"));

//        streamOfNamesStartsWithA.count();


        Stream<Course> courseStream = Stream
                .of(students)
                .flatMap(st -> st.getCourses().stream());
        courseStream
                .forEach(System.out::println);
        Stream<Stream<Course>> streamStream = Stream
                .of(students)
                .map(st -> st.getCourses().stream());


        List<String> studNames = Stream
                .of(students)
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("studNames = " + studNames);

        Function<Student, String> getName = Student::getName;

        Predicate<Student> isFemale = st -> st.getGender() == FEMALE;

        List<String> femaleNames = Stream
                .of(students)
                .filter(isFemale)
                .map(getName)
                .collect(Collectors.toList());
        System.out.println("femaleNames = " + femaleNames);

        Function<Object, String> toString = Object::toString;

        LinkedList<String> linkedList = Stream
                .of(students)
                .filter(isFemale)
                .map(getName)
                .collect(Collectors.toCollection(() -> new LinkedList<>()));

        System.out.println("linkedList = " + linkedList);

        Map<Gender, List<Student>> studentGroupByGender = Stream.of(students)
                .collect(Collectors.groupingBy(st -> st.getGender(), Collectors.toList()));

        System.out.println("studentGroupByGender = " + studentGroupByGender);


        Map<Gender, List<String>> studentNameGroupByGender =
                Stream
                        .of(students)
                        .collect(
                                Collectors.groupingBy(
                                        Student::getGender,
                                        Collectors.mapping(
                                                Student::getName,
                                                Collectors.toList())));


        System.out.println("studentNameGroupByGender = " + studentNameGroupByGender);

        Map<Gender, Long> groupOfStudentCountPerGender = Stream
                .of(students)
                .collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));

        System.out.println("groupOfStudentCountPerGender = " + groupOfStudentCountPerGender);


        List<Student> sortedStudents = Stream
                .of(students)
//                .sorted((st1, st2) -> st1.getName().compareTo(st2.getName()))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());

        List<Student> sortedStudentsReversed = Stream
                .of(students)
//                .sorted((st1, st2) -> st1.getName().compareTo(st2.getName()))
                .sorted(Comparator.comparing(Student::getName).reversed())
                .collect(Collectors.toList());

        System.out.println("sortedStudents = " + sortedStudents);
        System.out.println("sortedStudentsReversed = " + sortedStudentsReversed);

        List<String> sortedDesc = Stream
                .of(students)
                .sorted((st1, st2) -> st2.getName().compareTo(st1.getName()))
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println("sortedDesc = " + sortedDesc);

        ////////////////////////

        List<Student> stdList = asList(Application.students);

        Stream<Student> stream = stdList
                .stream();

        IntStream
//                .range(0,10)
                .rangeClosed(0,10)
                .forEach(System.out::println);


        int sum = IntStream
                .range(0, 11)
                .sum();

        System.out.println("sum = " + sum);


        IntStream
                .range(0,stdList.size())
                .mapToObj(index->stdList.get(index));

        OptionalInt max = IntStream
                .of(3, 4, 5, 100, 20, 10, 14)
                .max();
        System.out.println("max = " + max);
        List<Integer> collect = IntStream
                .of(3, 4, 5, 100, 20, 10, 14)
                .mapToObj(a->(Integer)a)
                .collect(Collectors.toList());
//                .collect(()->new ArrayList<Integer>(),(acc,e)->{acc.add(e);},(o1,o2)->{});


        OptionalInt max1 = IntStream
                .of(3, 4, 5, 100, 20, 10, 14)
                .filter(n -> n > 10000)
                .max();
        System.out.println("max1 = " + max1);


        Optional<Student> first = Stream
                .of(students)
                .findFirst();
        System.out.println("first = " + first);

        Optional<Student> first1 = Stream
                .of(students)
                .filter(st -> st.getAge() > 80)
                .findFirst();
        System.out.println("first1 = " + first1);


        Set<Course> setOfCoursed = Stream
                .of(students)
                .flatMap(st -> st.getCourses().stream())
                .collect(Collectors.toSet());

        System.out.println("setOfCoursed = " + setOfCoursed);

        //Stream.of
        //Collection.stram list.strea()

        //0 1 ,2 ,3
        Stream.iterate(0,a->a+1)
                .skip(5)
                .limit(10)
                .forEach(System.out::println);


    }
}
