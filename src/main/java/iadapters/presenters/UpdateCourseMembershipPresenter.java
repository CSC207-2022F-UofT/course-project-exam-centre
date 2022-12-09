package iadapters.presenters;

import iadapters.gateways.ViewManagerGateway;
import iadapters.exceptions.UpdateCourseMembershipFailed;
import iadapters.viewmodels.*;
import usecases.course.updatemembers.UpdateCMemOutputBoundary;
import usecases.course.updatemembers.UpdateCMemResponseModel;
import usecases.course.updatemembers.responsemodels.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UpdateCourseMembershipPresenter updates the views after the UpdateCourseMembership use case
 * @layer interface adapters
 */
public class UpdateCourseMembershipPresenter implements UpdateCMemOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public UpdateCourseMembershipPresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    private MessageTreeSubViewModel prepareMessageTreeModel(
            UpdateCMemMessageTreeResponseModel messageTreeResModel) {

        UpdateCMemUserResponseModel senderResModel
                = messageTreeResModel.getSender();

        List<MessageTreeSubViewModel> replies = new ArrayList<>();

        UserSubViewModel senderModel = new UserSubViewModel(
                senderResModel.getUserId(),
                senderResModel.getEmail(),
                senderResModel.getFirstName(),
                senderResModel.getLastName()
        );

        if (messageTreeResModel.getReplies().size() > 0) {
            for(UpdateCMemMessageTreeResponseModel replyMessageTreeResModel:
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

    private Map<String, SolutionDocSubViewModel> prepareSolutionModels(
            UpdateCMemTestDocResponseModel testDoResModel) {

        Map<String, SolutionDocSubViewModel> solutionViewModels = new HashMap<>();

        for(UpdateCMemSolutionDocResponseModel solutionResModel:
                testDoResModel.getSolutionModels().values()) {

            MessageTreeSubViewModel messageTreeModel
                    = prepareMessageTreeModel(solutionResModel.getRootMessage());

            solutionViewModels.put(
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
        }

        return solutionViewModels;

    }

    private Map<String, TestDocSubViewModel> prepareTestModels(
            UpdateCMemCourseResponseModel usersCourseResModel) {

        Map<String, TestDocSubViewModel> testViewModels = new HashMap<>();

        for(UpdateCMemTestDocResponseModel testResModel:
                usersCourseResModel.getTests().values()) {

            Map<String, SolutionDocSubViewModel> solutionModels
                    = prepareSolutionModels(testResModel);

            testViewModels.put(
                    testResModel.getTestId(),
                    new TestDocSubViewModel(
                            testResModel.getTestId(),
                            testResModel.getUserId(),
                            testResModel.getTestType(),
                            testResModel.getNumOfQuestions(),
                            testResModel.getEstimatedTime(),
                            testResModel.getTestName(),
                            solutionModels
                    )
            );
        }

        return testViewModels;

    }

    private Map<String, CourseSubViewModel> prepareCurrentUserCourseModels(
            Map<String, UpdateCMemCourseResponseModel> usersCourseResModels) {

        Map<String, CourseSubViewModel> courseViewModels = new HashMap<>();

        for(UpdateCMemCourseResponseModel courseResModel:
                usersCourseResModels.values()) {

            Map<String, TestDocSubViewModel> testModels
                    = prepareTestModels(courseResModel);

            courseViewModels.put(
                    courseResModel.getCourseId(),
                    new CourseSubViewModel(
                            courseResModel.getCourseId(),
                            courseResModel.getCourseCode(),
                            courseResModel.getCourseName(),
                            testModels
                    )
            );
        }
        return courseViewModels;
    }

    /** Prepares the successView when the membership of the course is successfully updated
     *
     * @param responseModel contains a user's course list and their membership status.
     * @return resposeModel corresponding to successfully updating course membership.
     */
    @Override
    public UpdateCMemResponseModel prepareSuccessView(
            UpdateCMemResponseModel responseModel) {

        UpdateCMemUserResponseModel currentUserResModel
                = responseModel.getCurrentUserModel();
        Map<String, UpdateCMemCourseResponseModel> usersCourseResModels
                = responseModel.getUsersCourseModels();

        viewModel.setCurrentUserModel(
                new UserSubViewModel(
                        currentUserResModel.getUserId(),
                        currentUserResModel.getEmail(),
                        currentUserResModel.getFirstName(),
                        currentUserResModel.getLastName()
                )
        );

        viewModel.setCurrentUserCourseModels(
                prepareCurrentUserCourseModels(usersCourseResModels)
        );

        viewManagerGateway.updateViews();

        return responseModel;
    }

    /** Prepares the successView when the membership of the course is unsuccessfully updated
     *
     * @param errorMessage String of the error that has occurred
     * @throws UpdateCourseMembershipFailed when the update course membership use case fails.
     */
    @Override
    public UpdateCMemResponseModel prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Course Membership Update Failed");
        throw new UpdateCourseMembershipFailed(errorMessage);
    }
}

