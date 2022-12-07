package uc.course.updatemembers;

import java.time.LocalDateTime;
import java.util.List;

/** UpdateCourseMembershipInteractor implements the ability to update a user's course membership.
 * @layer use cases
 */
public class UpdateCourseMembershipInteractor implements UpdateCMemInputBoundary {

    final UpdateCMemDsGateway gateway;
    final UpdateCMemOutputBoundary presenter;

    /** Constructs an UpdateCourseMembershipInteractor with a gateway and an OutputBoundary
     *
     * @param gateway provides methods to access persistent memory
     * @param presenter provides methods to update the view
     */
    public UpdateCourseMembershipInteractor(
            UpdateCMemDsGateway gateway,
            UpdateCMemOutputBoundary presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }


    @Override
    public UpdateCMemResponseModel updateCourseMembership(UpdateCMemRequestModel requestModel) {
        String userId = requestModel.getUserId();
        List<String> enrollments = gateway.getCourseIdsByUserId(userId);
        List<String> newCourseList = requestModel.getNewCoursesList();

        for (String course: newCourseList) { //Checks for new courses user not currently enrolled in.
            if (!enrollments.contains(course)) {
                if (gateway.checkIfCourseExists(course)) { //checks if a course has been registered
                    gateway.addCourseEnrolment(course, userId);
                }
                else {
                    return presenter.prepareFailView(course + " has not been registered");
                }
            }
        }
        for (String course: enrollments) { //Checks for removed courses
            if (!newCourseList.contains(course)) {
                gateway.removeCourseEnrolment(course, userId);
            }
        }
        LocalDateTime now = LocalDateTime.now();
        UpdateCMemResponseModel responseModel = new UpdateCMemResponseModel(now.toString(), newCourseList,true);
        return presenter.prepareSuccessView(responseModel);

    }
}