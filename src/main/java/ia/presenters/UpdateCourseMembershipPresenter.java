package ia.presenters;

import ia.gateways.ViewManagerGateway;
import ia.exceptions.UpdateCourseMembershipFailed;
import ia.viewmodels.MainViewModel;
import uc.course.updatemembers.UpdateCMemOutputBoundary;
import uc.course.updatemembers.UpdateCMemResponseModel;

public class UpdateCourseMembershipPresenter implements UpdateCMemOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public UpdateCourseMembershipPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
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

