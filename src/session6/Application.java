package session6;

import session5.step2.Course;
import session5.step2.Gender;
import session5.step2.Student;

import java.util.Map;
import java.util.stream.Collectors;
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
//        Map<Course, Map<Gender,Long>> result=

                Stream.of(students)
                .flatMap(st->st.getCourses().stream())


    }
}
