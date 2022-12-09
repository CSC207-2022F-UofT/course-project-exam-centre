package iadapters.presenters;

import iadapters.gateways.ViewManagerGateway;
import iadapters.exceptions.SubmitTestDocFailed;
import iadapters.viewmodels.*;
import usecases.doc.submittest.SubmitTDocOutputBoundary;
import usecases.doc.submittest.SubmitTDocResponseModel;
import usecases.doc.submittest.responsemodels.SubmitTDocTestDocResponseModel;

import java.util.HashMap;
import java.util.Map;

/**
 * SubmitTestDocPresenter updates the views after the SubmitTestDoc use case
 * @layer interface adapters
 */
public class SubmitTestDocPresenter implements SubmitTDocOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public SubmitTestDocPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    /** Prepares the successView when the test document is successfully submitted.
     *
     * @param responseModel A response model containing the information to be passed to the presenters for a success
     * @return resposeModel corresponding to successful submission of test document
     */
    @Override
    public SubmitTDocResponseModel prepareSuccessView(
            SubmitTDocResponseModel responseModel) {

        SubmitTDocTestDocResponseModel testResModel
                = responseModel.getTestDocModel();

        Map<String, CourseSubViewModel> courseModels
                = viewModel.getCurrentUserCourseModels();

        Map<String, TestDocSubViewModel> testModels
                = courseModels.get(responseModel.getParentCourseId()).getTests();

        testModels.put(
                testResModel.getTestId(),
                new TestDocSubViewModel(
                        testResModel.getTestId(),
                        testResModel.getUserId(),
                        testResModel.getTestType(),
                        testResModel.getNumOfQuestions(),
                        testResModel.getEstimatedTime(),
                        testResModel.getTestName(),
                        new HashMap<>()
                )
        );

        viewManagerGateway.updateViews();
        return responseModel;
    }

    /** Prepares the successView when the test document is unsuccessfully submitted.
     *
     * @param errorMessage The error that occurred
     * @throws SubmitTestDocFailed when the submit solution doc use case fails.
     */
    @Override
    public SubmitTDocResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Test Document Submission Failed");
        throw new SubmitTestDocFailed(errorMessage);
    }
}

