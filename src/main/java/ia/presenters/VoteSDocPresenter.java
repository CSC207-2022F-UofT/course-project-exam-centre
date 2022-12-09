package ia.presenters;

import ia.exceptions.VoteSDocFailed;
import ia.gateways.ViewManagerGateway;
import ia.viewmodels.*;
import uc.doc.submitsolution.responsemodels.SubmitSDocSolutionDocResponseModel;
import uc.doc.voteonsolution.VoteSDocOutputBoundary;
import uc.doc.voteonsolution.VoteSDocResponseModel;

import java.util.Map;

public class VoteSDocPresenter implements VoteSDocOutputBoundary{

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public VoteSDocPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    /** Prepares SuccessView when a solution document is successfully voted
     *
     * @param responseModel Response model containing the courseId, testId, solutionId and voteTotal
     * @return model Response model corresponding to successful
     */
    @Override
    public VoteSDocResponseModel prepareSuccessView(VoteSDocResponseModel responseModel) {

        Map<String, CourseSubViewModel> courseModels
                = viewModel.getCurrentUserCourseModels();

        Map<String, TestDocSubViewModel> testModels
                = courseModels.get(responseModel.getCourseId()).getTests();

        Map<String, SolutionDocSubViewModel> solutionModels
                = testModels.get(responseModel.getTestId()).getSolutionModels();


        solutionModels.get(responseModel.getSolutionId()).setVoteTotal(
                responseModel.getVoteTotal()
        );

        viewManagerGateway.updateViews();

        return responseModel;
    }

    /** Prepares SuccessView when a solution document is unsuccessfully voted
     *
     * @param errorMessage The errorMessage that occurs when a failure view happens
     * @return response model from use case
     */
    @Override
    public VoteSDocResponseModel prepareFailureView(String errorMessage) {
        // TODO: prepare failure view
        viewManagerGateway.showError(errorMessage, "Document Vote Failed");
        throw new VoteSDocFailed(errorMessage);
    }
    
}
