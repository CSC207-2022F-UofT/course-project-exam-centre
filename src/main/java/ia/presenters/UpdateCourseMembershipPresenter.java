package ia.presenters;

import fworks.views.UpdateCourseMembershipScreen;
import fworks.views.ViewManagerGateway;
import ia.exceptions.UpdateCourseMembershipFailed;
import uc.course.updatemembers.UpdateCMemOutputBoundary;
import uc.course.updatemembers.UpdateCMemResponseModel;

public class UpdateCourseMembershipPresenter implements UpdateCMemOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public UpdateCourseMembershipPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

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
     * @throws UpdateCourseMembershipFailed when the update course membership use case fails.
     */
    @Override
    public UpdateCMemResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Course Membership Update Failed");
        throw new UpdateCourseMembershipFailed(errorMessage);
    }
}

