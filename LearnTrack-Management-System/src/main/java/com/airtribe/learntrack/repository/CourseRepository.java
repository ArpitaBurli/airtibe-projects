package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private final List<Course> courseList = new ArrayList<>();

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public List<Course> viewAllCourses() {
        return courseList;
    }


    public Course findCourseById(String id) {
        return courseList.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public Course findCourseByName(String courseName) {

        return courseList.stream()
                .filter(course -> course.getCourseName().equalsIgnoreCase(courseName))
                .findFirst()
                .orElse(null);
    }
}
