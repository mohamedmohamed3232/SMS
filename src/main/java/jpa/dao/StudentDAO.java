package jpa.dao;

import org.mohamed.models.Course;
import org.mohamed.models.Student;

import java.util.List;
/*
This is crating the StudentDAO Interface to set up the methdods that would be used in the Student Service class
 */
public interface StudentDAO {
    List<Student> getAllStudents();
    Student getStudentByEmail(String email);
    void registerStudentToCourse(String email, int courseId);
    List<Course> getStudentCourse(String sEmail);
    boolean validateStudent(String sEmail, String sPassword);

}
