package iadapters.presenters;

import iadapters.gateways.ViewManagerGateway;
import iadapters.viewmodels.CourseInfoSubViewModel;
import iadapters.viewmodels.MainViewModel;
import usecases.course.register.CRegisterOutputBoundary;
import usecases.course.register.CRegisterResponseModel;
import iadapters.exceptions.CourseRegisterFailed;

import java.util.Map;

/**
 * CourseRegisterPresenter updates the views after CourseRegister use case
 * @layer interface adapters
 */
public class CourseRegisterPresenter implements CRegisterOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public CourseRegisterPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    /**  Prepares the successView when the Course is successfully registered
     *
     * @param responseModel CRegisterResponseModel containing the information of the new course
     * @return responseModel representing the newly registered course.
     */
    @Override
    public CRegisterResponseModel prepareSuccessView(
            CRegisterResponseModel responseModel) {

        Map<String, CourseInfoSubViewModel> courseInfoModels
                = viewModel.getCourseInfoModels();

        courseInfoModels.put(
                responseModel.getCourseId(),
                new CourseInfoSubViewModel(
                        responseModel.getCourseId(),
                        responseModel.getCourseCode(),
                        responseModel.getCourseName()
                ));

        viewManagerGateway.updateViews();

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

