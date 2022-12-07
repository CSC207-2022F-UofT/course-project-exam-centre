package ia.presenters;

import ia.exceptions.UpdateCourseMembershipFailed;
import uc.course.updatemembers.UpdateCMemOutputBoundary;
import uc.course.updatemembers.UpdateCMemResponseModel;

public class UpdateCourseMembershipPresenter implements UpdateCMemOutputBoundary {
    /** Prepares the successView when the membership of the course is successfully updated
     *
     * @param responseModel contains a user's course list and their membership status.
     * @return resposeModel corresponding to successfully updating course membership.
     */
    @Override
    public UpdateCMemResponseModel prepareSuccessView(
            UpdateCMemResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    /** Prepares the successView when the membership of the course is unsuccessfully updated
     *
     * @param errorMessage String of the error that has occurred
     * @return
     */
    @Override
    public UpdateCMemResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        throw new UpdateCourseMembershipFailed(errorMessage);
    }
}

