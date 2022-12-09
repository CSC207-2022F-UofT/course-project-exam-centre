package iadapters.presenters;

import iadapters.gateways.ViewManagerGateway;
import iadapters.exceptions.SubmitSolutionDocFailed;
import iadapters.viewmodels.*;
import usecases.doc.submitsolution.SubmitSDocOutputBoundary;
import usecases.doc.submitsolution.SubmitSDocResponseModel;
import usecases.doc.submitsolution.responsemodels.SubmitSDocMessageTreeResponseModel;
import usecases.doc.submitsolution.responsemodels.SubmitSDocSolutionDocResponseModel;
import usecases.doc.submitsolution.responsemodels.SubmitSDocUserResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SubmitSolutionDocPresenter updates the views after the SubmitSolutionDoc use case
 * @layer interface adapters
 */
public class SubmitSolutionDocPresenter implements SubmitSDocOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public SubmitSolutionDocPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    private MessageTreeSubViewModel prepareMessageTreeModel(
            SubmitSDocMessageTreeResponseModel messageTreeResModel) {

        SubmitSDocUserResponseModel senderResModel
                = messageTreeResModel.getSender();

        List<MessageTreeSubViewModel> replies = new ArrayList<>();

        UserSubViewModel senderModel = new UserSubViewModel(
                senderResModel.getUserId(),
                senderResModel.getEmail(),
                senderResModel.getFirstName(),
                senderResModel.getLastName()
        );

        if (messageTreeResModel.getReplies().size() > 0) {
            for(SubmitSDocMessageTreeResponseModel replyMessageTreeResModel:
                    messageTreeResModel.getReplies()) {
                replies.add(
                        prepareMessageTreeModel(replyMessageTreeResModel)
                );
            }
        }

        return new MessageTreeSubViewModel(
                messageTreeResModel.getMessageId(),
                senderModel,
                messageTreeResModel.getMessageBody(),
                messageTreeResModel.getMessageSentTimestamp(),
                replies
        );

    }

    /** Prepares the successView when the solution is successfully submitted.
     *
     * @param responseModel Bundled data to inform the user of the success
     * @return responseModel corresponding to successful submisison of solution.
     */
    @Override
    public SubmitSDocResponseModel prepareSuccessView(
            SubmitSDocResponseModel responseModel) {

        SubmitSDocSolutionDocResponseModel solutionResModel
                = responseModel.getSolutionDocModel();

        Map<String, CourseSubViewModel> courseModels
                = viewModel.getCurrentUserCourseModels();

        Map<String, TestDocSubViewModel> testModels
                = courseModels.get(responseModel.getParentCourseId()).getTests();

        Map<String, SolutionDocSubViewModel> solutionModels
                = testModels.get(responseModel.getParentTestId()).getSolutionModels();

        MessageTreeSubViewModel messageTreeModel
                = prepareMessageTreeModel(solutionResModel.getRootMessage());

        solutionModels.put(
                solutionResModel.getSolutionId(),
                new SolutionDocSubViewModel(
                        solutionResModel.getSolutionId(),
                        solutionResModel.getVoteTotal(),
                        solutionResModel.getRecordedScore(),
                        solutionResModel.getEstimatedTime(),
                        solutionResModel.getSolutionName(),
                        messageTreeModel
                )
        );

        viewManagerGateway.updateViews();

        return responseModel;
    }

    /** Prepares the failView when the solution is unsuccessfully submitted.
     *
     * @param errorMessage A string containing the error message
     * @throws SubmitSolutionDocFailed when the submit solution doc use case fails.
     */
    @Override
    public SubmitSDocResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Solution Document Submission Failed");
        throw new SubmitSolutionDocFailed(errorMessage);
    }
}

