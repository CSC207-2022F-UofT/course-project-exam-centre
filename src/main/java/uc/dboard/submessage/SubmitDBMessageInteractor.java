package uc.dboard.submessage;

import entities.*;

import java.time.LocalDateTime;

// Use case layer

public class SubmitDBMessageInteractor implements SubDBMessInputBoundary {

    // no gateway final UserRegisterDsGateway userDsGateway;
    final SubDBMessPresenter presenter;

    final SubDBMessDsGateway subDBMessDsGateway;

    final StateTracker stateTracker;

    public SubmitDBMessageInteractor(SubDBMessPresenter presenter,
                                     MessageFactory messageFactory, SubDBMessDsGateway subDBMessDsGateway, StateTracker stateTracker) {
        this.presenter = presenter;
        this.subDBMessDsGateway = subDBMessDsGateway;
        this.stateTracker = stateTracker;
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

        Message message = MessageFactory.create(
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
