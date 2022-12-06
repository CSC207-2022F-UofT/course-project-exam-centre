package uc.doc.submittest;

import entities.*;

import java.time.LocalDateTime;


/**
 * SubmitTestDocInteractor implementes the ability to save a test document into persistent memory
 * @layer Use cases
 */
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
     * The file access gateway for test document submission
     */
    private final SubTDocFileAccessGateway tDocFileAccessGateway;

    /**
     * The app's state tracker for finding courses/documents based on their ID
     */
    private final StateTracker stateTracker;

    /**
     * Creates a new interactor instance for submission of a new test document which contains an OutputBoundary,
     * DsGateway, FileAccessGateway, and StateTracker
     * @param tDocDsGateway Contains the methods to save the document in persistent memory
     * @param tDocOutputBoundary Provides the methods to update the views and pass information back to the user
     * @param tDocFileAccessGateway Provides methods for uploading the document
     * @param stateTracker The app's state tracker for referencing entities
     */
    public SubmitTestDocInteractor(SubTDocDsGateway tDocDsGateway,
                                   SubTDocFileAccessGateway tDocFileAccessGateway,
                                   SubTDocOutputBoundary tDocOutputBoundary,
                                   StateTracker stateTracker) {
        this.tDocOutputBoundary = tDocOutputBoundary;
        this.tDocDsGateway = tDocDsGateway;
        this.tDocFileAccessGateway = tDocFileAccessGateway;
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

        String docId = tDocDsGateway.saveTestDocument(dsRequestModel);
        
        tDocFileAccessGateway.uploadTestDocument(dsRequestModel, docId);

        TestDocument document = TestDocFactory.create(
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
