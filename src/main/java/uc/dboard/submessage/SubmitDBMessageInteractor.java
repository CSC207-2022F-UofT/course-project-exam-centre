package uc.dboard.submessage;

import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        String testId = subDBMessDsGateway.getDBTestIDFromSolutionID(solutionId);
        String courseId = subDBMessDsGateway.getDBCourseIDFromTestID(testId);
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

        String messageId = subDBMessDsGateway.addMessageToDB(dsRequestModel);

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
