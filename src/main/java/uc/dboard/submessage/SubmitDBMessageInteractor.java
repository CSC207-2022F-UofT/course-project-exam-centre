package uc.dboard.submessage;

import entities.*;
import entities.factories.MessageFactory;

import java.time.LocalDateTime;

// Use case layer

public class SubmitDBMessageInteractor implements SubDBMessInputBoundary {

    private final SubDBMessPresenter presenter;
    private final SubDBMessDsGateway subDBMessDsGateway;
    private final StateTracker stateTracker;
    private final MessageFactory messageFactory;

    public SubmitDBMessageInteractor(SubDBMessPresenter presenter,
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
