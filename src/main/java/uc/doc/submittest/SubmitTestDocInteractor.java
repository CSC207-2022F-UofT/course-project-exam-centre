package uc.doc.submittest;

import entities.*;

import java.time.LocalDateTime;

public class SubmitTestDocInteractor implements SubmitTDocInputBoundary {

    /**
     * The output boundary for the test document submission use case
     */
    private final SubTDocOutputBoundary tDocOutputBoundary;

    /**
     * The gateway for the test document submission use case
     */
    private final SubTDocDsGateway tDocDsGateway;

    /**
     * The app's state tracker for finding courses/documents based on their ID
     */
    private final StateTracker stateTracker;

    /**
     * Creates a new interactor instance for submission of a new test document
     * @param tDocDsGateway The test document submission gateway
     * @param tDocOutputBoundary The test document output boundary
     * @param stateTracker The app's state tracker for refrencing entites
     */
    public SubmitTestDocInteractor(SubTDocDsGateway tDocDsGateway,
                                   SubTDocOutputBoundary tDocOutputBoundary,
                                   StateTracker stateTracker) {
        this.tDocOutputBoundary = tDocOutputBoundary;
        this.tDocDsGateway = tDocDsGateway;
        this.stateTracker = stateTracker;
    }

    /**
     * Takes in information and creates a new TestDocument entity and returns feedback for the creation process
     * @param requestModel The request model containing the relevant information for the creation of a new TestDocument
     *                     entity
     * @return The response model creation of this entity
     */
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

        String docID = tDocDsGateway.saveTestDocument(dsRequestModel);

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
