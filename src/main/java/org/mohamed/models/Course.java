package org.mohamed.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/*
Creating the Course table using the Course Pojo below with all of its attributes
 */
@Entity
@Table
public class Course {
    public Course(){}

    public Course(int id, String name, String instructor){
    this.cId = id;
    this.cName = name;
    this.cInstructor = instructor;
    }

    @Id
    @Column(name = "id", nullable = false)
    int cId;
    @Column(length =50, name = "name", nullable = false)
    String cName;
    @Column(length = 50, name = "instructor",nullable = false)
    String cInstructor;

    public int getcId() {
        return cId;
    }

    public void setId(int id) {
        this.cId = id;
    }

    public String getName() {
        return cName;
    }

    public void setName(String name) {
        this.cName = name;
    }

    public String getCInstructor() {
        return cInstructor;
    }

    public void setInstructor(String instructor) {
        this.cInstructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + cId +
                ", name='" + cName + '\'' +
                ", Instructor='" + cInstructor + '\'' +
                '}';
    }
}
