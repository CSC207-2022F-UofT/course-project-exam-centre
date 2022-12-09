package uc.dboard.submessage;

import entities.*;
import entities.factories.MessageFactory;
import uc.dboard.submessage.dbmodels.SubDBMessUserDbModel;
import uc.dboard.submessage.responsemodels.SubDBMessMessageTreeResponseModel;
import uc.dboard.submessage.responsemodels.SubDBMessUserResponseModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** SubmitDBMessageInteractor implements the ability to submit a DB message
 * @layer use cases
 */
public class SubmitDBMessageInteractor implements SubDBMessInputBoundary {

    private final SubDBMessOutputBoundary presenter;
    private final SubDBMessDsGateway dsGateway;
    private final StateTracker currentState;
    private final MessageFactory messageFactory;

    /** Construct a SubmitDBMessageInteractor that contains an outputBoundary, DsGateway, stateTracker
     * and messageFactory
     *
     * @param presenter provides methods to update the view
     * @param messageFactory creates Message objects
     * @param dsGateway provides methods to access persistent data
     * @param currentState tracks the state of the entities accessed in the program
     */
    public SubmitDBMessageInteractor(SubDBMessOutputBoundary presenter,
                                     MessageFactory messageFactory,
                                     SubDBMessDsGateway dsGateway,
                                     StateTracker currentState) {
        this.presenter = presenter;
        this.dsGateway = dsGateway;
        this.currentState = currentState;
        this.messageFactory = messageFactory;
    }

    private SubDBMessMessageTreeResponseModel prepareMessageTreeResponseModel(
            MessageTree messageTree) {

        Message currentMessage = messageTree.getRootMessage();

        SubDBMessUserDbModel senderDbModel
                = dsGateway.getUserById(currentMessage.getUserId());

        List<SubDBMessMessageTreeResponseModel> replies = new ArrayList<>();

        SubDBMessUserResponseModel senderUserModel
                = new SubDBMessUserResponseModel(
                senderDbModel.getUserId(),
                senderDbModel.getEmail(),
                senderDbModel.getFirstName(),
                senderDbModel.getLastName()
        );

        if (messageTree.getSubtrees().size() > 0) {
            for(MessageTree replyMessageTree : messageTree.getSubtrees()) {
                replies.add(
                        prepareMessageTreeResponseModel(replyMessageTree)
                );
            }
        }

        return new SubDBMessMessageTreeResponseModel(
                currentMessage.getMessageId(),
                senderUserModel,
                currentMessage.getBody(),
                currentMessage.getDate(),
                replies
        );

    }

    @Override
    public SubDBMessResponseModel submitMessage(SubDBMessRequestModel requestModel) {

        // Exception handling for failed db connection
        if (!dsGateway.getConnectionStatus()) {
            return presenter.prepareFailView("Database Connection Failed");
        }

        String solutionId = requestModel.getSolutionId();
        String testId = dsGateway.getTestIdBySolutionId(solutionId);
        String courseId = dsGateway.getCourseIdByTestId(testId);
        Course course = currentState.getCourseIfTracked(courseId);

        TestDocument relatedTest = course.getTest(testId);
        SolutionDocument relatedSolution = relatedTest.getSolution(solutionId);

        LocalDateTime currentDateTime = LocalDateTime.now();
        SubDBMessDsRequestModel dsRequestModel = new SubDBMessDsRequestModel(
                solutionId,
                requestModel.getUserId(),
                requestModel.getParentId(),
                requestModel.getBody(),
                currentDateTime);

        String messageId = dsGateway.addMessage(dsRequestModel);

        Message message = messageFactory.create(
                messageId,
                solutionId,
                requestModel.getUserId(),
                requestModel.getParentId(),
                requestModel.getBody(),
                currentDateTime);
        relatedSolution.addMessage(message);

        MessageTree relatedMessageTree = relatedSolution.getMessageTree();

        SubDBMessMessageTreeResponseModel messageTreeModel
                = prepareMessageTreeResponseModel(relatedMessageTree);

        SubDBMessResponseModel messResponseModel = new SubDBMessResponseModel(
                messageTreeModel,
                relatedSolution.getId(),
                relatedSolution.getTest().getId(),
                relatedSolution.getTest().getCourse().getId()
        );
        return presenter.prepareSuccessView(messResponseModel);
    }


}
