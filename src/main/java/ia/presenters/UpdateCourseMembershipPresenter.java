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

    @Override
    public UpdateCMemResponseModel prepareSuccessView(
            UpdateCMemResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public UpdateCMemResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Course Membership Update Failed");
        throw new UpdateCourseMembershipFailed(errorMessage);
    }
}

