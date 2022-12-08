package ia.viewmodels;

import java.util.Map;

public class MainViewModel {

    private UserSubViewModel currentUserModel;
    private CourseMembershipViewModel courseMembershipViewModel;
    private Map<String, CourseSubViewModel> usersCourseModels;
    private Map<String, CourseInfoSubViewModel> courseInfoModels;

    MainViewModel(UserSubViewModel currentUser,
                  Map<String, CourseSubViewModel> usersCourseModels,
                  Map<String, CourseInfoSubViewModel> courseInfoModels) {

        this.currentUserModel = currentUser;
        this.usersCourseModels = usersCourseModels;
        this.courseInfoModels = courseInfoModels;
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

    public CourseMembershipViewModel getCourseMembershipViewModel() {
        return courseMembershipViewModel;
    }

    public void setCourseMembershipViewModel(CourseMembershipViewModel courseMembershipViewModel) {
        this.courseMembershipViewModel = courseMembershipViewModel;
    }
}
