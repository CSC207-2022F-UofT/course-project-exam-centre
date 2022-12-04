package uc.dboard.submessage;

import entities.*;
import entities.factories.MessageFactory;

import java.time.LocalDateTime;

/** SubmitDBMessageInteractor implements the ability to submit a DB message
 * @layer use cases
 */
public class SubmitDBMessageInteractor implements SubDBMessInputBoundary {

    private final SubDBMessOutputBoundary presenter;
    private final SubDBMessDsGateway subDBMessDsGateway;
    private final StateTracker stateTracker;
    private final MessageFactory messageFactory;

    /** Construct a SubmitDBMessageInteractor that contains an outputBoundary, DsGateway, stateTracker
     * and messageFactory
     *
     * @param presenter provides methods to update the view
     * @param messageFactory creates Message objects
     * @param subDBMessDsGateway provides methods to access persistent data
     * @param stateTracker tracks the state of the entities accessed in the program
     */
    public SubmitDBMessageInteractor(SubDBMessOutputBoundary presenter,
                                     MessageFactory messageFactory,
                                     SubDBMessDsGateway subDBMessDsGateway,
                                     StateTracker stateTracker) {
        this.presenter = presenter;
        this.subDBMessDsGateway = subDBMessDsGateway;
        this.stateTracker = stateTracker;
        this.messageFactory = messageFactory;
    }

    @Override
    public SubDBMessResponseModel submitMessage(SubDBMessRequestModel requestModel) {
        String solutionId = requestModel.getSolutionId();
        String testId = subDBMessDsGateway.getTestIdBySolutionId(solutionId);
        String courseId = subDBMessDsGateway.getCourseIdByTestId(testId);
        Course course = stateTracker.getCourseIfTracked(courseId);

        TestDocument relatedTest = course.getTest(testId);
        SolutionDocument relatedSolution = relatedTest.getSolution(solutionId);

        LocalDateTime currentDateTime = LocalDateTime.now();
        SubDBMessDsRequestModel dsRequestModel = new SubDBMessDsRequestModel(
                solutionId,
                requestModel.getUserId(),
                requestModel.getParentId(),
                requestModel.getBody(),
                currentDateTime);

        String messageId = subDBMessDsGateway.addMessage(dsRequestModel);

        Message message = messageFactory.create(
                messageId,
                solutionId,
                requestModel.getUserId(),
                requestModel.getParentId(),
                requestModel.getBody(),
                currentDateTime);
        relatedSolution.addMessage(message);
        SubDBMessResponseModel messResponseModel = new SubDBMessResponseModel(
                requestModel.getBody(),
                currentDateTime.toString(),
                requestModel.getUserId()
        );
        return presenter.prepareSuccessView(messResponseModel);
    }


}
