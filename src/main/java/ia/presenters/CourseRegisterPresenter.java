package ia.presenters;

import fworks.views.ViewManagerGateway;
import uc.course.register.CRegisterOutputBoundary;
import uc.course.register.CRegisterResponseModel;
import ia.exceptions.CourseRegisterFailed;

import javax.swing.text.View;

public class CourseRegisterPresenter implements CRegisterOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public CourseRegisterPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    @Override
    public CRegisterResponseModel prepareSuccessView(
            CRegisterResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    @Override
    public CRegisterResponseModel prepareFailView(String errorMessage) {
        viewManagerGateway.showError(errorMessage, "Course Register Failed");
        throw new CourseRegisterFailed(errorMessage);
    }
}

