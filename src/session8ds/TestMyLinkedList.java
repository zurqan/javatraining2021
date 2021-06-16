package session8ds;

import session5.step2.Student;

import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static session5.step2.Course.*;
import static session5.step2.Gender.MALE;

public class TestMyLinkedList {

    public static void main(String[] args) {

//        MyLinkedList<String> of = MyLinkedList.of("Ahmad","Ahmadq", "Mosa");
//        System.out.println("of.anyMatch(Predicate.isEqual(\"Ahmad\")) = " + of.anyMatch(Predicate.isEqual("Ahmad")));

        Student[] students = new Student[]{
                new Student("Ahmad", 25, MALE, false, asList(MATH, JAVA, ARABIC)),
                new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),

                new Student("Mosa", 20, MALE, true, asList(ENGLISH, ARABIC)),
                new Student("Ahmad", 25, MALE, false, asList(MATH, JAVA, ARABIC))
        };
        MyLinkedList<Student> of1 = MyLinkedList.of(students);

        boolean ahmad = of1.anyMatch(Predicate.isEqual(new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC))));
        System.out.println("ahmad = " + ahmad);

    }
}
