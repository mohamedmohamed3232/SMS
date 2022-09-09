package org.mohamed.models;

import jakarta.persistence.*;

import java.util.List;
/*
Creating the Student table with its attibutes, joining the student to the course table.
 */
@Entity
@Table
public class Student {
    public Student(){}

    public Student(String email, String name, String password) {
        this.sEmail = email;
        this.sName = name;
        this.sPassword = password;
    }

    @Id
    @Column(length =50, name = "email", nullable = false)
    private String sEmail;
    @Column(length = 50, name = "name", nullable = false)
    private String sName;
    @Column(length = 50, name = "password", nullable = false)
    private String sPassword;
//    @JoinTable(name = "student_course")
    @ManyToMany(targetEntity=Course.class, fetch=FetchType.EAGER)
   private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setSEmail(String email) {
        this.sEmail = email;
    }

    public String getSName() {
        return sName;
    }

    public void setName(String name) {
        this.sName = name;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setPassword(String password) {
        this.sPassword = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + sEmail + '\'' +
                ", name='" + sName + '\'' +
                ", password='" + sPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object stud) {
        if (stud instanceof Student) {
            Student stud1 = (Student) stud;
            boolean sameEmail = this.sEmail.equals(stud1.getsEmail());
            boolean sameName = this.sName.equals(stud1.getSName());
            boolean samePassword = this.sPassword.equals(stud1.getSPassword());
            boolean sameCourse = this.courses.equals(stud1.courses);
            if (sameEmail && sameName && samePassword && sameCourse) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
    }
    }
}
