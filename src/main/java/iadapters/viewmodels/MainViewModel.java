package iadapters.viewmodels;

import java.util.HashMap;
import java.util.Map;

public class MainViewModel {

    private UserSubViewModel currentUserModel;
    private Map<String, CourseSubViewModel> usersCourseModels;
    private Map<String, CourseInfoSubViewModel> courseInfoModels;
    private Map<String, String> localDocumentPaths;

    private String currentCourseId;
    private String currentTestId;
    private String currentSolutionId;

    public MainViewModel() {
        this.localDocumentPaths = new HashMap<>();
    }

    public String getCurrentCourseId() {
        return this.currentCourseId;
    }

    public void setCurrentCourseId(String newCurrentCourseId) {
        this.currentCourseId = newCurrentCourseId;
    }

    public String getCurrentTestId() {
        return this.currentTestId;
    }

    public void setCurrentTestId(String newCurrentTestId) {
        this.currentTestId = newCurrentTestId;
    }

    public String getCurrentSolutionId() {
        return this.currentSolutionId;
    }

    public void setCurrentSolutionId(String newCurrentSolutionId) {
        this.currentSolutionId = newCurrentSolutionId;
    }

    public UserSubViewModel getCurrentUserModel() {
        return this.currentUserModel;
    }

    public Map<String, CourseSubViewModel> getCurrentUserCourseModels() {
        return this.usersCourseModels;
    }

    public Map<String, CourseInfoSubViewModel> getCourseInfoModels() {
        return this.courseInfoModels;
    }

    public void setCurrentUserModel(UserSubViewModel currentUserModel) {
        this.currentUserModel = currentUserModel;
    }

    public void setCurrentUserCourseModels(
            Map<String, CourseSubViewModel> usersCourseModels) {
        this.usersCourseModels = usersCourseModels;
    }

    public void setCourseInfoModels(
            Map<String, CourseInfoSubViewModel> courseInfoModels) {
        this.courseInfoModels = courseInfoModels;
    }

    public Map<String, String> getLocalDocumentPaths() {
        return this.localDocumentPaths;
    }

    public void setLocalDocumentPaths(Map<String, String> newLocalDocumentPaths) {
        this.localDocumentPaths = newLocalDocumentPaths;
    }

    public boolean checkIfLocalDocumentPathExists(String documentId) {
        return this.localDocumentPaths.containsKey(documentId);
    }

    public String getLocalDocumentPath(String documentId) {
        return this.localDocumentPaths.get(documentId);
    }

    public void reset() {
        this.currentUserModel = null;
        this.usersCourseModels = null;
        this.courseInfoModels = null;
        this.localDocumentPaths = null;
        this.currentCourseId = null;
        this.currentTestId = null;
        this.currentSolutionId = null;
    }

}
