package assignment;


import session6.Application;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static assignment.Course.*;
import static assignment.CourseGrade.of;
import static assignment.Gender.FEMALE;
import static assignment.Gender.MALE;
import static java.util.Arrays.asList;

public class Assignment4 {

    static Student[] students = new Student[]{
            new Student("Ahmad", 25, MALE, false, asList(of(MATH,90), of(JAVA,80), of(ARABIC,85))),
            new Student("Mohammad", 31, MALE, false, asList(of(MATH,70), of(ENGLISH,30), of(ARABIC,90))),
            new Student("Mosa", 20, MALE, true, asList(of(MATH,50), of(ENGLISH,65), of(ARABIC,100))),
            new Student("Heba", 20, FEMALE, false, asList(of(MATH,40), of(CHEMISTRY,99), of(ARABIC,30))),
            new Student("Ruba", 30, FEMALE, true, asList(of(MATH,100), of(JAVA,80), of(ARABIC,77))),
            new Student("Esa", 45, MALE, false, asList(of(MATH,77), of(CHEMISTRY,88), of(ARABIC,82)))
    };


    public static List<Student> getMaleStudent(){
        //add your implementation here
        //use stream to achieve the result
        return null;
    }

    public static List<Student> getActiveStudent(){
        //add your implementation here
        //use stream to achieve the result
        return null;
    }

    public static Map<Gender, Map<Boolean,List<Student>>> groupStudentByGenderAndStatus(){
        //add your implementation here
        //use stream to achieve the result
        return null;
    }

    public static List<Student> getStudentsWhoTakeChemistry(){
        //add your implementation here
        //use stream to achieve the result
        return null;
    }

    public static List<Application.Tuple<String,Double>> getStudentNameWithNetGrade(){
        //add your implementation here
        //use stream to achieve the result
        return null;
    }

    public static Set<Course> getSetOfCoursesThatStudentsToked(){
        //add your implementation here
        //use stream to achieve the result
        //hint: What type of set you will use?
        return         Stream.of(students).flatMap(s->s.getCourseGrades().stream().map(g->g.getCourse()))
                .collect(Collectors.toCollection(()-> EnumSet.noneOf(Course.class)))
                ;
    }

    /**
     * return the student who achieve  Math grade is the closet to the targetGrade value,
     * Student's Math grade should be grater or equal to the targetGrade value
     * @param targetGrade
     * @return
     */
    public static Optional<Student> getStudentWithMathGradeCloseToThePassedGrade(int targetGrade){
        //add your implementation here

        return null;

    }

    public static void main(String[] args) {

    }


}
