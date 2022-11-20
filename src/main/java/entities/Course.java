package entities;

import java.util.ArrayList;
import java.util.List;

import entities.TestDoc;

public class Course {
    private final String courseName;
    private final String courseCode;
    private final List<TestDoc> tests;
    private final String courseId;

    public Course(String courseName, String courseCode, String courseId) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.tests = new ArrayList<TestDoc>();
        this.courseId = courseId;

    }
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void addTest(TestDoc test) {
        this.tests.add(test);
    }
    public void removeTest(TestDoc test) {
        this.tests.remove(test);
    }
}
