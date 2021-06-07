package session6;

import session5.step2.Course;
import session5.step2.Gender;
import session5.step2.Student;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
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

        Map<Course, Map<Gender, Long>> groupOfCourseByGender = Stream
                .of(students)
                .flatMap(st -> st.getCourses().stream().map(c -> new Tuple<Course, Student>(c, st)))
//                .flatMap(st -> st.getCourses().stream().map(c -> new Test(c, st)))
//                .flatMap(st -> st.getCourses().stream().map(c -> new CourseStudent(c, st)))
                .collect(groupingBy(tuple -> tuple._1, groupingBy(t -> t._2.getGender(), counting())));


        System.out.println("groupOfCourseByGender = " + groupOfCourseByGender);

        Tuple<String,String> t = new  Tuple<>("Ahmad","Mohammad");

        Tuple<Integer, Integer> lengthStr = t.map(String::length, String::length);


        System.out.println("lengthStr = " + lengthStr);

        Tuple<Integer, String> t1 = t.map1(String::length);
        System.out.println("t1 = " + t1);

        Tuple<String, Integer> t3 = t.map2(String::length);
        System.out.println("t3 = " + t3);
    }

    public static class Tuple<T,U> {
        public final T _1;
        public final U _2;

        public Tuple(T _1, U _2) {
            this._1 = _1;
            this._2 = _2;
        }

        public <A,B> Tuple<A,B> map(Function<T,A> _f1/*T->A*/, Function<U,B> _f2){

            return new Tuple<A,B>(_f1.apply(_1),_f2.apply(_2));

        }

        public <A> Tuple<A,U> map1(Function<T,A> _f1){

            return new Tuple<>(_f1.apply(_1),_2);
        }

        public <B> Tuple<T,B> map2(Function<U,B> _f2){
            return new Tuple<>(_1,_f2.apply(_2));
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "_1=" + _1 +
                    ", _2=" + _2 +
                    '}';
        }
    }
    public static class Test {
        public final Course _1;
        public final Student _2;

        public Test(Course _1, Student _2) {
            this._1 = _1;
            this._2 = _2;
        }

    }

    public static class CourseStudent extends  Tuple<Course,Student>{
        public CourseStudent(Course _1, Student _2) {
            super(_1, _2);
        }
    }
}
