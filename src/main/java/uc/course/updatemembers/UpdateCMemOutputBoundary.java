package uc.course.updatemembers;

/** UpdateCMemOutputBoundary is provides methods for the UpdateCourseMembershipInteractor to output data
 * @layer use cases
 */
public interface UpdateCMemOutputBoundary {
    UpdateCMemResponseModel updateCourseMembership(UpdateCMemRequestModel requestModel);
}
