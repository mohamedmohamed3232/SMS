package jpa.dao;

import org.mohamed.models.Course;

import java.util.List;
/*
This is the CourseDAO Interface to set up the models for Course Service.
 */
public interface CourseDAO {
    List<Course> getAllCourses();

}
