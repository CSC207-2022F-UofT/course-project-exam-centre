package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.UpdateCourseMembershipFailed;
import uc.course.updatemembers.UpdateCMemOutputBoundary;
import uc.course.updatemembers.UpdateCMemResponseModel;

public class UpdateCourseMembershipPresenter implements UpdateCMemOutputBoundary {
    @Override
    public UpdateCMemResponseModel prepareSuccessView(
            UpdateCMemResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public UpdateCMemResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        ViewManagerGateway.showError(errorMessage, "Course Membership Update Failed");
        throw new UpdateCourseMembershipFailed(errorMessage);
    }
}

