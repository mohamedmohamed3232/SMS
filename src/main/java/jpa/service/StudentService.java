package jpa.service;

import jpa.dao.StudentDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mohamed.models.Course;
import org.mohamed.models.Student;

import java.util.List;
/*
This is the Service class which overrides the StudentDAO Interface methods
 */

public class StudentService implements StudentDAO {
/*
This is a method that creates a session to then use the query to select all students from the database
Then the session is closed as is the factory.
 */
    @Override
    public List<Student> getAllStudents() {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    List<Student> students = session.createQuery("select s from Student s ").getResultList();
    session.close();
    factory.close();
    return students;
    }
    /*
    This is an overrided method that takes an email from the user and retrives the student associated with that email.
     */
    @Override
    public Student getStudentByEmail(String email) {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    Student searchStudent = session.get(Student.class, email);
    session.close();
    factory.close();
    return searchStudent;
    }
    /*
    This is the method that will register the student to the course that they will pick
     */
    @Override
    public void registerStudentToCourse(String email, int cId) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Course rCourse = session.get(Course.class, cId);
        Student rStudent = session.get(Student.class, email);
        List<Course> courses = rStudent.getCourses();
            if(courses.contains(rCourse)) {
                System.out.println("You are already registred to the course");

            } else {
                courses.add(rCourse);
                rStudent.setCourses(courses);
                session.persist(rStudent);
                t.commit();
                session.close();
                factory.close();
            }
        }

/*
This is the method that will get the studentcourse by first getting the student and then adding that students course into the courselist
 */
    @Override
    public List<Course> getStudentCourse(String email) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Student gStudent = session.get(Student.class, email);
        List<Course> courseList = gStudent.getCourses();
        session.close();
        factory.close();
        return courseList;
        }
/*
This is my method to validate the student I get the email and passwerd and then get that students email from the database and check if they match
 */
    @Override
    public boolean validateStudent(String email, String password) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Student vStudent = session.get(Student.class, email);
        if(vStudent.getSPassword().contentEquals(password)) {
            System.out.println("Verified");
          return true;
        } else {
            session.close();
            factory.close();
            return false;
        }

    }



}
