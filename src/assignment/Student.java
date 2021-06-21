package assignment;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {

    private final String name;

    private final int age;

    private final Gender gender;

    private final boolean active;

    private final List<CourseGrade> courseGrades;

    public Student(String name, int age, Gender gender, boolean active, List<CourseGrade> courseGrades) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.active = active;
        this.courseGrades = courseGrades;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isActive() {
        return active;
    }

    public List<CourseGrade> getCourseGrades() {
        return new ArrayList<>(courseGrades);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", active=" + active +
                ", courseGrades=" + courseGrades +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getAge() == student.getAge() &&
                isActive() == student.isActive() &&
                getName().equals(student.getName()) &&
                getGender() == student.getGender() &&
                Objects.equals(getCourseGrades(), student.getCourseGrades());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }
}
