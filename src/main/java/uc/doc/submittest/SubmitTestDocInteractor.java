package uc.doc.submittest;

import entities.*;
import entities.factories.TestDocFactory;
import uc.doc.submittest.responsemodels.SubmitTDocTestDocResponseModel;

import java.util.HashMap;

/**
 * SubmitTestDocInteractor implements the ability to save a test document into persistent memory
 * @layer Use cases
 */
public class    SubmitTestDocInteractor implements SubmitTDocInputBoundary {

    private final SubmitTDocOutputBoundary tDocOutputBoundary;
    private final SubmitTDocDsGateway dsGateway;
    private final SubmitTDocFileAccessGateway tDocFileAccessGateway;
    private final TestDocFactory testDocFactory;
    private final StateTracker stateTracker;

    /**
     * Creates a new interactor instance for submission of a new test document which contains an OutputBoundary,
     * DsGateway, FileAccessGateway, and StateTracker
     * @param tDocDsGateway Contains the methods to save the document in persistent memory
     * @param tDocOutputBoundary Provides the methods to update the views and pass information back to the user
     * @param tDocFileAccessGateway Provides methods for uploading the document
     * @param dsGateway The app's state tracker for referencing entities
     * @param testDocFactory The factory for creating test documents
     */
    public SubmitTestDocInteractor(SubmitTDocDsGateway tDocDsGateway,
                                   SubmitTDocFileAccessGateway tDocFileAccessGateway,
                                   SubmitTDocOutputBoundary tDocOutputBoundary,
                                   StateTracker dsGateway,
                                   TestDocFactory testDocFactory) {
        this.tDocOutputBoundary = tDocOutputBoundary;
        this.dsGateway = tDocDsGateway;
        this.tDocFileAccessGateway = tDocFileAccessGateway;
        this.stateTracker = dsGateway;
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

        // Exception handling for failed db connection
        if (!dsGateway.getConnectionStatus()) {
            return tDocOutputBoundary.prepareFailView("Database Connection Failed");
        }

        Course course = stateTracker.getCourseIfTracked(requestModel.getCourseID());
        User user = stateTracker.getCurrentUser();

        SubmitTDocDsRequestModel dsRequestModel = new SubmitTDocDsRequestModel(
                requestModel.getName(),
                requestModel.getNumberOfQuestions(),
                requestModel.getRecordedTime(),
                requestModel.getTestType(),
                user.getId(),
                requestModel.getCourseID(),
                requestModel.getFilePath()
        );

        String docId = dsGateway.saveTestDocument(dsRequestModel);
        
        if (!tDocFileAccessGateway.checkConnectionStatus()) {
            return tDocOutputBoundary.prepareFailView("Error connecting to the FTP server");
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
        SubmitTDocTestDocResponseModel testDocModel
                = new SubmitTDocTestDocResponseModel(
                    document.getId(),
                    document.getUser().getId(),
                    document.getTestType(),
                    document.getNumberOfQuestions(),
                    document.getEstimatedTime(),
                    document.getName(),
                    new HashMap<>()
        );

        SubmitTDocResponseModel responseModel = new SubmitTDocResponseModel(
                testDocModel,
                document.getCourse().getId());

        return tDocOutputBoundary.prepareSuccessView(responseModel);
    }

}
