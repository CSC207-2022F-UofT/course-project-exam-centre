package uc.doc.submittest;

import entities.*;
import entities.factories.TestDocFactory;

import java.time.LocalDateTime;

public class SubmitTestDocInteractor implements SubmitTDocInputBoundary {

    private final SubTDocOutputBoundary tDocOutputBoundary;
    private final SubTDocDsGateway tDocDsGateway;
    private final SubTDocFileAccessGateway tDocFileAccessGateway;
    private final StateTracker stateTracker;
    private final TestDocFactory testDocFactory;

    public SubmitTestDocInteractor(SubTDocDsGateway tDocDsGateway,
                                   SubTDocFileAccessGateway tDocFileAccessGateway,
                                   SubTDocOutputBoundary tDocOutputBoundary,
                                   StateTracker stateTracker,
                                   TestDocFactory testDocFactory) {
        this.tDocOutputBoundary = tDocOutputBoundary;
        this.tDocDsGateway = tDocDsGateway;
        this.tDocFileAccessGateway = tDocFileAccessGateway;
        this.stateTracker = stateTracker;
        this.testDocFactory = testDocFactory;
    }

    @Override
    public SubmitTDocResponseModel submitTestDoc(SubTDocRequestModel requestModel) {
        Course course = stateTracker.getCourseIfTracked(requestModel.getCourseID());
        User user = stateTracker.getUserIfTracked(requestModel.getUserID());

        SubTDocDsRequestModel dsRequestModel = new SubTDocDsRequestModel(
                requestModel.getName(),
                requestModel.getNumberOfQuestions(),
                requestModel.getRecordedTime(),
                requestModel.getTestType(),
                requestModel.getUserID(),
                requestModel.getCourseID(),
                requestModel.getFilePath()
        );

        String docId = tDocDsGateway.saveTestDocument(dsRequestModel);
        
        tDocFileAccessGateway.uploadTestDocument(dsRequestModel, docId);

        TestDocument document = testDocFactory.create(
                requestModel.getName(),
                docId,
                course,
                user,
                requestModel.getRecordedTime(),
                requestModel.getNumberOfQuestions(),
                requestModel.getTestType()
        );

        course.addTest(document);

        SubmitTDocResponseModel responseModel = new SubmitTDocResponseModel(docId, LocalDateTime.now());

        return tDocOutputBoundary.prepareSuccessView(responseModel);
    }

}
