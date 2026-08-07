package com.airtribe.learntrack.util;

public class IdGenerator {

    private static int studentCounter = 0;
    private static int courseCounter = 0;
    private static int enrollmentCounter = 0;

    public static String generateStudentId() {
        studentCounter++;
        return "S" + studentCounter;
    }

    public static String generateCourseId() {
        courseCounter++;
        return "C" + courseCounter;
    }

    public static String generateEnrollmentId() {
        enrollmentCounter++;
        return "E" + enrollmentCounter;
    }


}
