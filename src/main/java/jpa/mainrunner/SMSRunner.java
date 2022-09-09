package jpa.mainrunner;

import jpa.service.CourseService;
import jpa.service.StudentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mohamed.models.Course;
import org.mohamed.models.Student;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/*
Hello Ms. Lewis this is where the main logic to run the application lives please refer to the readme file I have pasted a copy of it here as well  Thanks!

Hello Ms. Lewis

Thanks for taking your time to look over my SBA a few quick notes if you can update the hibernate.cfg.xml file with the database you would like to use.
Also please make sure you change your username and password if they do not match mine.
I created this project using IntelliJ so let me know if you run into any issues you should not since this is a maven project.
Also double check the version of MariaDB is correct for both the pom file and the hibernate file.
All of the Students and courses are located in the SMSRunner class as well as in the instructions for the SBA.
If you have any issues please let me know and let me know your feedback.
Thanks,
Mohamed Mohamed

 */


public class SMSRunner {

    private Scanner scanner;
    private StringBuilder stringB;
    private CourseService courseService;
    private StudentService studentService;
    private Student searchStudent;

    public SMSRunner() {
        scanner = new Scanner(System.in);
        stringB = new StringBuilder();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    public void createTableValues(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
       Student stud1 = new Student("hluckham0@google.ru","Hazel Luckham", "X1uZcoIh0dj"  );
       Student stud2 = new Student("sbowden1@yellowbook.com","Sonnnie Bowden", "SJc4aWSU"  );
       Student stud3 = new Student("qllorens2@howstuffworks.com","Quillan Llorens", "W6rJuxd"  );
        Student stud4 = new Student("cstartin3@flickr.com","Clem Startin", "XYHzJ1S"  );
        Student stud5 = new Student("tattwool4@biglobe.ne.jp","Thornie Attwool", "Hjt0SoVmuBz"  );
        Student stud6 = new Student("hguerre5@deviantart.com","Harcourt Guerre", "OzcxzD1PGs"  );
        Student stud7 = new Student("htaffley6@columbia.edu","Holmes Taffley", "xowtOQ"  );
        Student stud8 = new Student("aiannitti7@is.gd","Alexandra Iannitti", "TWP4hf5j"  );
        Student stud9 = new Student("ljiroudek8@sitemeter.com","Laryssa Jiroudek", "bXRoLUP"  );
        Student stud10 = new Student("cjaulme9@bing.com","Cahra Jaulme", "FnVklVgC6r6"  );
        session.saveOrUpdate(stud1);
        session.saveOrUpdate(stud2);
        session.saveOrUpdate(stud3);
        session.saveOrUpdate(stud4);
        session.saveOrUpdate(stud5);
        session.saveOrUpdate(stud6);
        session.saveOrUpdate(stud7);
        session.saveOrUpdate(stud8);
        session.saveOrUpdate(stud9);
        session.saveOrUpdate(stud10);
        Course course1 = new Course(1, "English", "Andrea Scamaden");
        Course course2 = new Course(2, "Mathematics", "Eustace Niemetz");
        Course course3 = new Course(3, "Anatomy", "Reynolds Pastor");
        Course course4 = new Course(4, "Organic Chemistry", "Odessa Belcher");
        Course course5 = new Course(5, "Physics", "Dani Swallow");
        Course course6 = new Course(6, "Digital Logic", "Glenden Reilingen");
        Course course7 = new Course(7, "Object Oriented Programming", "Giselle Ardy");
        Course course8 = new Course(8, "Data Structures", "Carolan Stoller");
        Course course9 = new Course(9, "Politics", "Andrea Scamaden");
        Course course10 = new Course(10, "Art", "Kingsly Doxsey");
        session.saveOrUpdate(course1);
        session.saveOrUpdate(course2);
        session.saveOrUpdate(course3);
        session.saveOrUpdate(course4);
        session.saveOrUpdate(course5);
        session.saveOrUpdate(course6);
        session.saveOrUpdate(course7);
        session.saveOrUpdate(course8);
        session.saveOrUpdate(course9);
        session.saveOrUpdate(course10);
        t.commit();
        session.close();
        factory.close();
    }


    public static void main(String[] args) {
        SMSRunner sms = new SMSRunner();
        sms.createTableValues();
        sms.start();

    }

    private void start() {
        // Simple login and quit menu
        try {
        switch (menu1()) {
            case 1:

                    if (studentLogin()) {
                        registerMenu();
                    }
                    break;
                    case 2:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Please only use 1 or 2 try again");
                }
                }catch (InputMismatchException e ) {
            System.out.println("Please only use 1 or 2 try again");
        }
        }

    private int menu1() {
        stringB.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
        System.out.println(stringB.toString());
        stringB.delete(0, stringB.length());
        return scanner.nextInt();
    }

    private boolean studentLogin() {
        boolean logValue = false;
        System.out.println("Enter your email address: ");
        String email = scanner.next();
        System.out.println("Enter your password: ");
        String password = scanner.next();
        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            searchStudent = student;
        }

        if (searchStudent != null && searchStudent.getSPassword().equals(password)) {
            List<Course> courses = studentService.getStudentCourse(email);
            System.out.println("Your Classes:");
            System.out.printf("%-6s%-37s%-25s\n", "#", "COURSE NAME", "INSTRUCTOR NAME");
            for (Course course : courses) {
                System.out.printf("%-6s%-37s%-25s\n", course.getcId(), course.getName(), course.getCInstructor());
                System.out.println();
            }
            logValue = studentService.validateStudent(searchStudent.getsEmail(), searchStudent.getSPassword());
        } else {
            System.out.println("\"User Validation failed. GoodBye!");
        }
        return logValue;
    }

    private void registerMenu() {
        stringB.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        System.out.println(stringB.toString());
        stringB.delete(0, stringB.length());



            try {
            switch (scanner.nextInt()) {
                case 1:
                    List<Course> allCourses = courseService.getAllCourses();
                    List<Course> studentCourses;
                    studentCourses = studentService.getStudentCourse(searchStudent.getsEmail());
                    allCourses.removeAll(studentCourses);
                    System.out.println("All Courses: ");
                    System.out.printf("%-6s%-37s%-25s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
                    for (Course course : allCourses)
                        System.out.printf("%-6s%-37s%-25s\n", course.getcId(), course.getName(), course.getCInstructor());
                    System.out.println("Enter Course Number: ");
                    int number = scanner.nextInt();

                    if(number > 10 || number < 1) {
                        System.out.println("Please enter a valid number try again ");
                        break;
                    }
                    Course newCourse = courseService.getAllCourses().get(number);

                    if (newCourse != null   ) {
                        studentService.registerStudentToCourse(searchStudent.getsEmail(), number);
                        Student temp = studentService.getStudentByEmail(searchStudent.getsEmail());
                        List<Course> sCourses = studentService.getStudentCourse(temp.getsEmail());

                        System.out.println("My Classes:");
                        System.out.printf("%-6s%-37s%-25s\n", "ID", "Course", "Instructor");
                        for (Course course : sCourses) {
                            System.out.printf("%-6s%-37s%-25s\n", course.getcId(), course.getName(), course.getCInstructor());
                        }
                        System.out.println("Class successfully registered");
                    }
                    registerMenu();

                case 2:
                    System.out.println("Good bye");
                    break;
                default:
                    System.out.println("Goodbye!");


            }
        } catch (InputMismatchException  e){
            System.out.println("Please try again at a different time and make sure you use only the correct numbers to navigate");
        }

    }
}