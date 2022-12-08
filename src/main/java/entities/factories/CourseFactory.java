package entities.factories;

import entities.Course;

public class CourseFactory {
    public Course create(String courseName, String courseCode, String courseId) {
        return new Course(courseName, courseCode, courseId);
    }
}
