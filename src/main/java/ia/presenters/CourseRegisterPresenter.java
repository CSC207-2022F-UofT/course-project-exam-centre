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

    /**  Prepares the successView when the Course is successfully registered
     *
     * @param responseModel CRegisterResponseModel containing the information of the new course
     * @return responseModel representing the newly registered course.
     */
    @Override
    public CRegisterResponseModel prepareSuccessView(
            CRegisterResponseModel responseModel) {
        // TODO: Update view model here
        return responseModel;
    }

    /** Prepares the successView when the Course is unsuccessfully registered
     *
     * @param errorMessage A string of the error that has occurred
     * @throws CourseRegisterFailed occurs when the course register use case fails.
     */
    @Override
    public CRegisterResponseModel prepareFailView(String errorMessage) {
        //TODO Update view model here
        viewManagerGateway.showError(errorMessage, "Course Register Failed");
        throw new CourseRegisterFailed(errorMessage);
    }
}

