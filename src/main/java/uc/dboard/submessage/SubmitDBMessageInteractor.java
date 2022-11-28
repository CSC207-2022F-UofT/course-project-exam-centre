package uc.dboard.submessage;

import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

// Use case layer

public class SubmitDBMessageInteractor implements SubDBMessInputBoundary {

    // no gateway final UserRegisterDsGateway userDsGateway;
    final SubDBMessPresenter presenter;
    final MessageFactory messageFactory;

    final SubDBMessDsGateway subDBMessDsGateway;

    final StateTracker stateTracker;

    public SubmitDBMessageInteractor(SubDBMessPresenter presenter,
                                     MessageFactory messageFactory, SubDBMessDsGateway subDBMessDsGateway, StateTracker stateTracker) {
        this.presenter = presenter;
        this.messageFactory = messageFactory;
        this.subDBMessDsGateway = subDBMessDsGateway;
        this.stateTracker = stateTracker;
    }

    @Override
    public SubDBMessResponseModel submitMessage(SubDBMessRequestModel requestModel) {
        String solutionId = requestModel.getSolutionId();
        String testId = subDBMessDsGateway.getDBTestIDFromSolutionID(solutionId);
        String courseId = subDBMessDsGateway.getDBCourseIDFromTestID(testId);
        Course course = stateTracker.getCourseIfTracked(courseId);

        //Below Code Traverses through Arraylist to find the test that corresponds to where the message should be added

        //Course should have a tests attribute that is an ArrayList
        ArrayList<TestDocument> tests = course.tests;
        TestDocument relatedTest = null;
        for (TestDocument test:tests){
            if (test.getId().equals(testId)){
                relatedTest = test;
                break;
            }
        }

        //Below Code Traverses through Arraylist to find the solution that corresponds to where the message should be added

        //TestDocument should have a solutions attribute that is an ArrayList of Solutions and not just a SolutionDocument
        ArrayList<SolutionDocument> solutions = relatedTest.getSolutions();
        SolutionDocument relatedSolution = null;
        for (SolutionDocument solution:solutions){
            if (solution.getId().equals(solutionId)){
                relatedSolution = solution;
                break;
            }
        }
        // Since we are no longer having a discussion board we should have the SolutionDocument have an attribute that stores a list of Messages
        LocalDateTime now = LocalDateTime.now();
        SubDBMessDsRequestModel dsRequestModel = new SubDBMessDsRequestModel(solutionId, requestModel.getUserId(), requestModel.getParentId(), requestModel.getBody(), now);
        String messageId = subDBMessDsGateway.addMessageToDB(dsRequestModel);
        Message message = messageFactory.create(messageId, solutionId, requestModel.getUserId(), requestModel.getParentId(), requestModel.getBody(), now);
        relatedSolution.addMessage(message);
        // Update State Tracker with changed Solution Document with the Added Message.
        SubDBMessResponseModel messResponseModel = new SubDBMessResponseModel(requestModel.getBody(), now.toString(), requestModel.getUserId());
        return presenter.prepareSuccessView(messResponseModel);
    }


}