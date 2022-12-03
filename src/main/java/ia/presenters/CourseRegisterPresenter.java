package ia.presenters;

import uc.course.register.CRegisterOutputBoundary;
import uc.course.register.CRegisterResponseModel;
import uc.course.register.CourseRegisterException;
import uc.user.login.LoginFailedException;
import uc.user.login.LoginOutputBoundary;
import uc.user.login.LoginResponseModel;

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

