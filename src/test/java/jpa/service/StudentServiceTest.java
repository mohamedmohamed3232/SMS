package jpa.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class StudentServiceTest {
/*
Runnig a test to see if a student is valid
 */
    @Test
    void validateStudent() {
        StudentService studentService = new StudentService();
        boolean expected = studentService.validateStudent("ljiroudek8@sitemeter.com", "bXRoLUP");
        // I took a student off the valid list so this should be valid
        Assert.assertTrue(expected);
        System.out.println("This is a valid Student");
    }


}