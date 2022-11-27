package uc.course.updatemembers;

import java.util.List;

public class UpdateCMemRequestModel {
    private String userId;
    private List<String> newCoursesList;

    public UpdateCMemRequestModel(String userId, List<String> newCoursesList) {
        this.userId = userId;
        this.newCoursesList = newCoursesList;
    }


    public String getUserId() {
        return userId;
    }

    public List<String> getNewCoursesList() {
        return newCoursesList;
    }
}
