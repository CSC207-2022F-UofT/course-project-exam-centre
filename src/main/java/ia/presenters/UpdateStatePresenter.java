package ia.presenters;

import ia.gateways.ViewManagerGateway;
import ia.exceptions.UpdateStateFailed;
import ia.viewmodels.*;
import uc.state.update.UpdateStateOutputBoundary;
import uc.state.update.UpdateStateResponseModel;
import uc.state.update.responsemodels.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateStatePresenter implements UpdateStateOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;
    private final MainViewModel viewModel;

    /**
     * Creates a presenter for updating the view
     * @param viewManagerGateway Used for managing and updating views
     */
    public UpdateStatePresenter(
            ViewManagerGateway viewManagerGateway, MainViewModel viewModel) {
        this.viewManagerGateway = viewManagerGateway;
        this.viewModel = viewModel;
    }

    private Map<String, CourseInfoSubViewModel> prepareCourseInfoModels(
            Map<String, UpdateStateCourseInfoResponseModel> courseInfoResModels) {

        Map<String, CourseInfoSubViewModel> courseInfoViewModels = new HashMap<>();

        for(UpdateStateCourseInfoResponseModel courseInfoResModel:
                courseInfoResModels.values()) {
            courseInfoViewModels.put(
                    courseInfoResModel.getCourseId(),
                    new CourseInfoSubViewModel(
                            courseInfoResModel.getCourseId(),
                            courseInfoResModel.getCourseCode(),
                            courseInfoResModel.getCourseName()
                    )
            );
        }

        return courseInfoViewModels;

    }

    private MessageTreeSubViewModel prepareMessageTreeModel(
            UpdateStateMessageTreeResponseModel messageTreeResModel) {

        UpdateStateUserResponseModel senderResModel
                = messageTreeResModel.getSender();

        List<MessageTreeSubViewModel> replies = new ArrayList<>();

        UserSubViewModel senderModel = new UserSubViewModel(
                senderResModel.getUserId(),
                senderResModel.getEmail(),
                senderResModel.getFirstName(),
                senderResModel.getLastName()
        );

        if (messageTreeResModel.getReplies().size() > 0) {
            for(UpdateStateMessageTreeResponseModel replyMessageTreeResModel:
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
            UpdateStateTestDocResponseModel testDoResModel) {

        Map<String, SolutionDocSubViewModel> solutionViewModels = new HashMap<>();

        for(UpdateStateSolutionDocResponseModel solutionResModel:
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
            UpdateStateCourseResponseModel usersCourseResModel) {

        Map<String, TestDocSubViewModel> testViewModels = new HashMap<>();

        for(UpdateStateTestDocResponseModel testResModel:
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
            Map<String, UpdateStateCourseResponseModel> usersCourseResModels) {

        Map<String, CourseSubViewModel> courseViewModels = new HashMap<>();

        for(UpdateStateCourseResponseModel courseResModel:
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

    /** Prepares FailView when the state is successfully updated.
     *
     * @param responseModel representing data to update view models
     * @return responseModel corresponding to succesful update of state.
     */
    @Override
    public UpdateStateResponseModel prepareSuccessView(
            UpdateStateResponseModel responseModel){

        UpdateStateUserResponseModel currentUserResModel
                = responseModel.getCurrentUserModel();
        Map<String, UpdateStateCourseResponseModel> usersCourseResModels
                = responseModel.getCurrentUserCourseModels();
        Map<String, UpdateStateCourseInfoResponseModel> courseInfoResModels
                = responseModel.getCourseInfoModels();

        if (currentUserResModel != null) {
            viewModel.setCurrentUserModel(
                    new UserSubViewModel(
                            currentUserResModel.getUserId(),
                            currentUserResModel.getEmail(),
                            currentUserResModel.getFirstName(),
                            currentUserResModel.getLastName()
                    )
            );
        }

        viewModel.setCourseInfoModels(
                prepareCourseInfoModels(courseInfoResModels)
        );

        viewModel.setCurrentUserCourseModels(
            prepareCurrentUserCourseModels(usersCourseResModels)
        );

        viewManagerGateway.updateViews();

        return responseModel;
    }

    /** Prepares FailView when the state is unsuccessfully updated.
     *
     * @param errorMessage String of the error that occured.
     * @throws UpdateStateFailed when the state is not successfully updated.
     */
    @Override
    public UpdateStateResponseModel prepareFailView(String errorMessage){
        // TODO: Update view model
        viewManagerGateway.showError(errorMessage, "State Update Failed");
        throw new UpdateStateFailed(errorMessage);
    }

}
