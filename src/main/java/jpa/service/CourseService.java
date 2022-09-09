package jpa.service;

import jpa.dao.CourseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mohamed.models.Course;

import java.util.List;

public class CourseService implements CourseDAO {
    /*
    This is a method to get all of the courses from the Course table in the database it is overrided from the CourseDao interface
     */
    @Override
    public List<Course> getAllCourses() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        List<Course> courseList = session.createQuery("select c from Course c", Course.class).getResultList();
        session.close();
        return courseList;
    }


    }

