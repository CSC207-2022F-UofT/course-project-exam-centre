package iadapters.presenters;

import iadapters.gateways.ViewManagerGateway;
import iadapters.exceptions.SubmitDBMessageFailed;
import iadapters.viewmodels.*;
import usecases.submessage.SubDBMessOutputBoundary;
import usecases.submessage.SubDBMessResponseModel;
import usecases.submessage.responsemodels.SubDBMessMessageTreeResponseModel;
import usecases.submessage.responsemodels.SubDBMessUserResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SubmitDBMessagePresenter updates the views after the SubmitDBMessage use case
 * @layer interface adapters
 */
public class SubmitDBMessagePresenter implements SubDBMessOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public SubmitDBMessagePresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    private MessageTreeSubViewModel prepareMessageTreeModel(
            SubDBMessMessageTreeResponseModel messageTreeResModel) {

        SubDBMessUserResponseModel senderResModel
                = messageTreeResModel.getSender();

        List<MessageTreeSubViewModel> replies = new ArrayList<>();

        UserSubViewModel senderModel = new UserSubViewModel(
                senderResModel.getUserId(),
                senderResModel.getEmail(),
                senderResModel.getFirstName(),
                senderResModel.getLastName()
        );

        if (messageTreeResModel.getReplies().size() > 0) {
            for(SubDBMessMessageTreeResponseModel replyMessageTreeResModel:
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

    /** Prepares the successView when the message is successfully submitted.
     *
     * @param responseModel a responseModel containing the body, creation time, and parentId
     * @return responseModel corresponding to successful submission of message.
     */
    @Override
    public SubDBMessResponseModel prepareSuccessView(
            SubDBMessResponseModel responseModel) {

        Map<String, CourseSubViewModel> courseModels
                = viewModel.getCurrentUserCourseModels();

        MessageTreeSubViewModel messageTreeModel
                = prepareMessageTreeModel(responseModel.getMessageTree());

        Map<String, TestDocSubViewModel> testModels
                = courseModels.get(responseModel.getParentCourseId()).getTests();

        Map<String, SolutionDocSubViewModel> solutionModels
                = testModels.get(responseModel.getParentTestId()).getSolutionModels();

        SolutionDocSubViewModel solutionModel
                = solutionModels.get(responseModel.getSolutionId());

        SolutionDocSubViewModel newSolutionModel
                = new SolutionDocSubViewModel(
                        solutionModel.getSolutionId(),
                        solutionModel.getVoteTotal(),
                        solutionModel.getRecordedScore(),
                        solutionModel.getEstimatedTime(),
                        solutionModel.getSolutionName(),
                        messageTreeModel
                );

        solutionModels.put(
                responseModel.getSolutionId(),
                newSolutionModel
        );

        viewManagerGateway.updateViews();

        return responseModel;
    }

    /** Prepares the failView when the message is unsuccessfully submitted.
     *
     * @param errorMessage String containing the error
     * @throws SubmitDBMessageFailed when the submit discussion message use case fails.
     */
    @Override
    public SubDBMessResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Discussion Board Message Submission Failed");
        throw new SubmitDBMessageFailed(errorMessage);
    }
}

