package uc.course.updatemembers;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateCourseMembership implements UpdateCMemInputBoundary {

    final UpdateCMemDsGateway gateway;
    final UpdateCMemPresenter presenter;

    public UpdateCourseMembership(UpdateCMemDsGateway gateway, UpdateCMemPresenter presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }


    @Override
    public UpdateCMemResponseModel updateCourseMembership(UpdateCMemRequestModel requestModel) {
        String userId = requestModel.getUserId();
        List<String> enrollments = gateway.getCourseEnrollment(userId);
        List<String> newCourseList = requestModel.getNewCoursesList();

        for (String course: newCourseList) { //Checks for new courses user not currently enrolled in.
            if (!enrollments.contains(course)) {
                if (gateway.checkIfCourseExists(course)) { //checks if a course has been registered
                    gateway.addCourseEnrollment(course, userId);
                }
                else {
                    return presenter.prepareFailView(course + " has not been registered");
                }
            }
        }
        for (String course: enrollments) { //Checks for removed courses
            if (!newCourseList.contains(course)) {
                gateway.removeCourseEnrollment(course, userId);
            }
        }
        LocalDateTime now = LocalDateTime.now();
        UpdateCMemResponseModel responseModel = new UpdateCMemResponseModel(now.toString(), newCourseList,true);
        return presenter.prepareSuccessView(responseModel);

    }
}
