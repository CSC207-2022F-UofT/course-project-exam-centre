package uc.course.updatemembers;

/** UpdateCMemOutputBoundary is provides methods for the UpdateCourseMembershipInteractor to output data
 * @layer use cases
 */
public interface UpdateCMemOutputBoundary {
    /**
     * Prepares a success view
     * @param responseModel contains a user's course list and their membership status.
     */
    UpdateCMemResponseModel prepareSuccessView(UpdateCMemResponseModel responseModel);

    /**
     * Prepares a failure view
     * @param errorMessage String of the error that has occurred
     * @throws ia.exceptions.UpdateCourseMembershipFailed exception
     */
    UpdateCMemResponseModel prepareFailView(String errorMessage);
}
