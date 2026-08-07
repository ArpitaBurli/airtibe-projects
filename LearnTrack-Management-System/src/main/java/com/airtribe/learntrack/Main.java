package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class Main {
    private static final StudentRepository studentRepository =
            new StudentRepository();

    private static final CourseRepository courseRepository =
            new CourseRepository();

    private static final EnrollmentRepository enrollmentRepository =
            new EnrollmentRepository();

    private static final StudentService studentService =
            new StudentService(studentRepository);

    private static final CourseService courseService =
            new CourseService(courseRepository);

    private static final EnrollmentService enrollmentService =
            new EnrollmentService(
                    enrollmentRepository,
                    studentRepository,
                    courseRepository
            );

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        boolean isRunning = true;

        while (isRunning) {

            displayMainMenu();

            out.print("Enter your choice: ");

            int choice = InputValidator.readMenuChoice(sc, 0, 3);

            switch (choice) {

                case MenuOptions.STUDENT_MANAGEMENT:
                    studentMenu(sc);
                    break;

                case MenuOptions.COURSE_MANAGEMENT:
                    courseMenu(sc);
                    break;

                case MenuOptions.ENROLLMENT_MANAGEMENT:
                    enrollmentMenu(sc);
                    break;

                case MenuOptions.EXIT:
                    isRunning = false;
                    out.println("Thank you for using LearnTrack!");
                    break;

                default:
                    out.println("Invalid Choice.");
            }
        }

        sc.close();
    }


    public static void displayMainMenu() {

        out.println("\n==========================");
        out.println("        LearnTrack");
        out.println("==========================");
        out.println("1. Student Management");
        out.println("2. Course Management");
        out.println("3. Enrollment Management");
        out.println("0. Exit");
    }

    public static void studentMenu(Scanner sc) {

        boolean isStudentMenuRunning = true;

        while (isStudentMenuRunning) {

            out.println("\n========== Student Management ==========");
            out.println("1. Add New Student");
            out.println("2. View All Students");
            out.println("3. Search Student by ID");
            out.println("4. Deactivate Student");
            out.println("0. Back");
            out.print("Enter your choice: ");

            int choice = InputValidator.readMenuChoice(sc, 0, 4);

            try {

                switch (choice) {

                    case MenuOptions.ADD_STUDENT:

                        out.println("\n" + AppConstants.SECTION_LINE);
                        out.println("                  ADD NEW STUDENT");
                        out.println(AppConstants.SECTION_LINE);

                        out.print("Enter First Name: ");
                        String firstName = InputValidator.readNonEmptyString(sc);

                        out.print("Enter Last Name: ");
                        String lastName = InputValidator.readNonEmptyString(sc);

                        out.print("Enter Email: ");
                        String email = InputValidator.readValidEmail(sc);

                        out.print("Enter Joining Year: ");
                        int batch = InputValidator.readInt(sc);

                        studentService.addStudent(firstName, lastName, email, batch);

                        out.println("Student added successfully.");
                        break;

                    case MenuOptions.VIEW_STUDENTS:
                        out.println("\n" + AppConstants.SECTION_LINE);
                        out.println("                    STUDENT LIST");
                        out.println(AppConstants.SECTION_LINE);

                        List<Student> students = studentService.viewAllStudents();

                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {

                            displayStudents(students);
                        }

                        break;
                    case MenuOptions.SEARCH_STUDENT:

                        out.print("Enter Student ID: ");
                        String id = InputValidator.readNonEmptyString(sc);

                        Student student = studentService.searchStudentById(id);

                        out.println(student);

                        break;

                    case MenuOptions.DEACTIVATE_STUDENT:

                        out.print("Enter Student ID: ");
                        id = InputValidator.readNonEmptyString(sc);

                        boolean deactivated = studentService.deactivateStudent(id);

                        if (deactivated) {
                            out.println("Student deactivated successfully.");
                        } else {
                            out.println("Student is already inactive.");
                        }

                        break;

                    case MenuOptions.EXIT:

                        isStudentMenuRunning = false;
                        break;

                    default:

                        out.println("Invalid Choice.");
                }

            } catch (InvalidInputException | EntityNotFoundException e) {

                out.println(e.getMessage());

            }
        }
    }

    public static void courseMenu(Scanner sc) {

        boolean isCourseMenuRunning = true;

        while (isCourseMenuRunning) {

            out.println("\n========== Course Management ==========");
            out.println("1. Add New Course");
            out.println("2. View All Courses");
            out.println("3. Activate / Deactivate Course");
            out.println("0. Back");
            out.print("Enter your choice: ");

            int choice = InputValidator.readMenuChoice(sc, 0, 3);

            try {

                switch (choice) {

                    case MenuOptions.ADD_COURSE:

                        out.println("\n" + AppConstants.SECTION_LINE);
                        out.println("                  ADD NEW COURSE");
                        out.println(AppConstants.SECTION_LINE);

                        out.print("Enter Course Name: ");
                        String courseName = InputValidator.readNonEmptyString(sc);

                        out.print("Enter Course Duration (Weeks): ");
                        int durationInWeeks = InputValidator.readInt(sc);

                        out.print("Enter Course Description: ");
                        String description = InputValidator.readNonEmptyString(sc);

                        Course course = courseService.addCourse(
                                courseName,
                                durationInWeeks,
                                description);

                        out.println("Course added successfully.");
                        out.println("Course ID : " + course.getId());

                        break;

                    case MenuOptions.VIEW_COURSES:

                        out.println("\n" + AppConstants.SECTION_LINE);
                        out.println("                    COURSE LIST");
                        out.println(AppConstants.SECTION_LINE);

                        List<Course> courses = courseService.viewAllCourses();

                        if (courses.isEmpty()) {
                            out.println("No courses found.");
                        } else {
                            displayCourses(courses);

                        }

                        break;

                    case MenuOptions.UPDATE_COURSE_STATUS:

                        out.print("Enter Course ID: ");
                        String id = InputValidator.readNonEmptyString(sc);

                        out.println("1. Activate");
                        out.println("2. Deactivate");
                        out.print("Enter your choice: ");

                        int statusChoice = InputValidator.readMenuChoice(sc, 1, 2);

                        if (statusChoice == 1) {
                            courseService.updateCourseStatus(id, true);
                        } else {
                            courseService.updateCourseStatus(id, false);
                        }

                        break;

                    case MenuOptions.EXIT:

                        isCourseMenuRunning = false;
                        break;

                    default:

                        out.println("Invalid Choice.");
                }

            } catch (InvalidInputException | EntityNotFoundException e) {

                out.println(e.getMessage());

            }
        }
    }

    public static void enrollmentMenu(Scanner sc) {

        boolean isEnrollmentMenuRunning = true;

        while (isEnrollmentMenuRunning) {

            out.println("\n========== Enrollment Management ==========");
            out.println("1. Enroll Student in a Course");
            out.println("2. View Enrollments for a Student");
            out.println("3. Mark Enrollment as Completed");
            out.println("4. Cancel Enrollment");
            out.println("0. Back");
            out.print("Enter your choice: ");

            int choice = InputValidator.readMenuChoice(sc, 0, 4);

            try {

                switch (choice) {

                    case MenuOptions.ENROLL_STUDENT:

                        System.out.println("\n" + AppConstants.SECTION_LINE);
                        System.out.println("                  ENROLL STUDENT");
                        System.out.println(AppConstants.SECTION_LINE);
                        out.println("\n Available Students");
                        List<Student> students = studentService.viewAllStudents();

                        for (Student s : students) {
                            out.println(s);
                        }

                        out.println("  Available Courses");
                        List<Course> courses = courseService.viewAllCourses();

                        for (Course c : courses) {
                            out.println(c);
                        }

                        out.print("Enter Student ID: ");
                        String studentId = InputValidator.readNonEmptyString(sc);

                        out.print("Enter Course ID: ");
                        String courseId = InputValidator.readNonEmptyString(sc);

                        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);

                        out.println("Student enrolled successfully.");
                        out.println("Enrollment ID: " + enrollment.getId());

                        break;

                    case MenuOptions.VIEW_ENROLLMENTS:
                        out.println("\n------ View Enrollments for Student ------");

                        out.print("Enter Student ID: ");
                        studentId = InputValidator.readNonEmptyString(sc);

                        List<Enrollment> enrollments =
                                enrollmentService.viewEnrollmentsByStudentId(studentId);
                        out.printf("%-15s %-12s %-20s %-12s %-20s %-12s%n",
                                "Enrollment ID", "Student ID", "Student Name",
                                "Course ID", "Course Name", "Status");
                        out.println("-----------------------------------------------------------------------------------------------");

                        for (Enrollment e : enrollments) {
                            out.printf("%-15s %-12s %-20s %-12s %-20s %-12s%n",
                                    e.getId(),
                                    e.getStudent().getId(),
                                    e.getStudent().getFirstName() + " " + e.getStudent().getLastName(),
                                    e.getCourse().getId(),
                                    e.getCourse().getCourseName(),
                                    e.getStatus());
                        }
                        break;
                    case MenuOptions.COMPLETE_ENROLLMENT:

                        out.println("\n------ Complete Enrollment ------");


                        out.println("Enter enrollment id :");
                        String enrollmentId = InputValidator.readNonEmptyString(sc);
                        enrollmentService.updateEnrollmentStatus(
                                enrollmentId,
                                EnrollmentStatus.COMPLETED
                        );

                        out.println("Enrollment completed successfully.");

                        break;

                    case MenuOptions.CANCEL_ENROLLMENT:

                        out.println("\n------ Cancel Enrollment ------");

                        out.println("Enter enrollment id :");
                        enrollmentId = InputValidator.readNonEmptyString(sc);
                        enrollmentService.updateEnrollmentStatus(
                                enrollmentId,
                                EnrollmentStatus.CANCELLED
                        );
                        out.println("Enrollment cancelled successfully.");
                        break;

                    case MenuOptions.EXIT:

                        isEnrollmentMenuRunning = false;
                        break;

                    default:

                        out.println("Invalid Choice.");
                }

            } catch (InvalidInputException | EntityNotFoundException e) {

                out.println(e.getMessage());

            }
        }
    }

    private static void displayStudents(List<Student> students) {

        out.printf("%-6s %-25s %-30s %-8s %-8s%n",
                "ID", "Name", "Email", "Batch", "Active");
        out.println("-------------------------------------------------------------------------------");

        for (Student student : students) {
            out.printf("%-6s %-25s %-30s %-8d %-8s%n",
                    student.getId(),
                    student.getFirstName() + " " + student.getLastName(),
                    student.getEmail(),
                    student.getBatch(),
                    student.isActive());
        }
    }

    private static void displayCourses(List<Course> courses) {

        out.printf("%-6s %-25s %-35s %-8s %-8s%n",
                "ID", "Course Name", "Description", "Weeks", "Active");
        out.println("---------------------------------------------------------------------------------------------");

        for (Course course : courses) {
            out.printf("%-6s %-25s %-35s %-8d %-8s%n",
                    course.getId(),
                    course.getCourseName(),
                    course.getDescription(),
                    course.getDurationInWeeks(),
                    course.isActive());
        }
    }
}
