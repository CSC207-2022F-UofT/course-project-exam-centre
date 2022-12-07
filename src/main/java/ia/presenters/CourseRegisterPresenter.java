package ia.presenters;

import fworks.views.ViewManagerGateway;
import uc.course.register.CRegisterOutputBoundary;
import uc.course.register.CRegisterResponseModel;
import ia.exceptions.CourseRegisterFailed;

public class CourseRegisterPresenter implements CRegisterOutputBoundary {
    @Override
    public CRegisterResponseModel prepareSuccessView(
            CRegisterResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public CRegisterResponseModel prepareFailView(String errorMessage) {
        ViewManagerGateway.showError(errorMessage, "Course Register Failed");
        throw new CourseRegisterFailed(errorMessage);
    }
}

