package uc.doc.submittest;

import entities.*;
import entities.factories.TestDocFactory;

import java.time.LocalDateTime;

/**
 * SubmitTestDocInteractor implements the ability to save a test document into persistent memory
 * @layer Use cases
 */
public class SubmitTestDocInteractor implements SubmitTDocInputBoundary {

    private final SubmitTDocOutputBoundary tDocOutputBoundary;

    private final SubmitTDocDsGateway tDocDsGateway;

    private final SubmitTDocFileAccessGateway tDocFileAccessGateway;

    private final TestDocFactory testDocFactory;

    private final StateTracker stateTracker;

    /**
     * Creates a new interactor instance for submission of a new test document which contains an OutputBoundary,
     * DsGateway, FileAccessGateway, and StateTracker
     * @param tDocDsGateway Contains the methods to save the document in persistent memory
     * @param tDocOutputBoundary Provides the methods to update the views and pass information back to the user
     * @param tDocFileAccessGateway Provides methods for uploading the document
     * @param stateTracker The app's state tracker for referencing entities
     * @param testDocFactory The factory for creating test documents
     */
    public SubmitTestDocInteractor(SubmitTDocDsGateway tDocDsGateway,
                                   SubmitTDocFileAccessGateway tDocFileAccessGateway,
                                   SubmitTDocOutputBoundary tDocOutputBoundary,
                                   StateTracker stateTracker,
                                   TestDocFactory testDocFactory) {
        this.tDocOutputBoundary = tDocOutputBoundary;
        this.tDocDsGateway = tDocDsGateway;
        this.tDocFileAccessGateway = tDocFileAccessGateway;
        this.stateTracker = stateTracker;
        this.testDocFactory = testDocFactory;
    }

    /**
     * Takes in information and creates a new TestDocument entity and returns feedback for the creation process
     * @param requestModel The request model containing the relevant information for the creation of a new TestDocument
     *                     entity
     * @return The response model creation of this entity
     */
    @Override
    public SubmitTDocResponseModel submitTestDoc(SubmitTDocRequestModel requestModel) {
        Course course = stateTracker.getCourseIfTracked(requestModel.getCourseID());
        User user = stateTracker.getUserIfTracked(requestModel.getUserID());

        SubmitTDocDsRequestModel dsRequestModel = new SubmitTDocDsRequestModel(
                requestModel.getName(),
                requestModel.getNumberOfQuestions(),
                requestModel.getRecordedTime(),
                requestModel.getTestType(),
                requestModel.getUserID(),
                requestModel.getCourseID(),
                requestModel.getFilePath()
        );

        String docId = tDocDsGateway.saveTestDocument(dsRequestModel);
        
        if (!tDocFileAccessGateway.checkConnectionStatus()) {
            return tDocOutputBoundary.prepareFailureView("Error connecting to the FTP server");
        }
        
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
