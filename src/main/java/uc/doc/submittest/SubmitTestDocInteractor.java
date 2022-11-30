package uc.doc.submittest;

import entities.*;

import java.time.LocalDateTime;

public class SubmitTestDocInteractor implements SubmitTDocInputBoundary {

    private final SubTDocOutputBoundary tDocOutputBoundary;

    private final SubTDocDsGateway tDocDsGateway;

    private final StateTracker stateTracker;

    public SubmitTestDocInteractor(SubTDocDsGateway tDocDsGateway,
                                   SubTDocOutputBoundary tDocOutputBoundary,
                                   StateTracker stateTracker) {
        this.tDocOutputBoundary = tDocOutputBoundary;
        this.tDocDsGateway = tDocDsGateway;
        this.stateTracker = stateTracker;
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

        String docID = tDocDsGateway.saveTestDoc(dsRequestModel);

        TestDocument document = TestDocFactory.create(
                requestModel.getName(),
                docID,
                course,
                user,
                requestModel.getRecordedTime(),
                requestModel.getNumberOfQuestions(),
                requestModel.getTestType()
        );

        course.addTest(document);

        SubmitTDocResponseModel responseModel = new SubmitTDocResponseModel(docID, LocalDateTime.now());

        return tDocOutputBoundary.prepareSuccessView(responseModel);
    }

}
