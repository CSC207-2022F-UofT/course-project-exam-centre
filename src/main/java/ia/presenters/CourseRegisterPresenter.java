package ia.presenters;

import uc.course.register.CRegisterOutputBoundary;
import uc.course.register.CRegisterResponseModel;
import ia.exceptions.CourseRegisterException;

public class CourseRegisterPresenter implements CRegisterOutputBoundary {
    @Override
    public CRegisterResponseModel prepareSuccessView(
            CRegisterResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public CRegisterResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        throw new CourseRegisterException(errorMessage);
    }
}

