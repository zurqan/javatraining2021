package assignment;

public class CourseGrade {
    final Course course;
    final int grade;

    public CourseGrade(Course course, int grade) {
        this.course = course;
        this.grade = grade;
    }

    public static CourseGrade of(Course course,int grade){
        return new CourseGrade(course, grade);
    }

    public Course getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }
}
