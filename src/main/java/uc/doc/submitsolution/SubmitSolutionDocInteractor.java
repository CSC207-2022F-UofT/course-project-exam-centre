package uc.doc.submitsolution;

import entities.*;

import java.time.LocalDateTime;

public class SubmitSolutionDocInteractor implements SubmitSDocInputBoundary{

    private final SubSDocOutputBoundary sDocOutputBoundary;

    private final SubSDocDsGateway sDocDsGateway;

    private final SubSDocFileAccessGateway sDocFileAccessGateway;

    private final StateTracker stateTracker;

    public SubmitSolutionDocInteractor(SubSDocDsGateway sDocDsGateway,
                                       SubSDocFileAccessGateway sDocFileAccessGateway,
                                       SubSDocOutputBoundary sDocOutputBoundary,
                                       StateTracker stateTracker) {
        this.sDocDsGateway = sDocDsGateway;
        this.sDocFileAccessGateway = sDocFileAccessGateway;
        this.sDocOutputBoundary = sDocOutputBoundary;
        this.stateTracker = stateTracker;
    }

    @Override
    public SubSDocResponseModel submitSolutionDoc(SubSDocRequestModel model) {
        Course course  = stateTracker.getCourseIfTracked(model.getCourseID());
        User user = stateTracker.getUserIfTracked(model.getUserID());
        TestDocument parentTest = course.getTest(model.getParentTestID());

        SubSDocDsRequestModel dsRequestModel = new SubSDocDsRequestModel(
                model.getName(),
                model.getUserID(),
                model.getRecordedScore(),
                model.getCourseID(),
                model.getParentTestID(),
                model.getFilePath()
        );

        String docId = sDocDsGateway.saveSolutionDocument(dsRequestModel);
        sDocFileAccessGateway.uploadSolutionDocument(dsRequestModel, docId);

        SolutionDocument document = SolutionDocFactory.create(
                model.getName(),
                docId,
                course,
                user,
                model.getRecordedScore(),
                parentTest,
                model.getRecordedTime(),
                model.getRootID());

        parentTest.addUpdateSolution(document);

        SubSDocResponseModel responseModel = new SubSDocResponseModel(document.getId(), parentTest.getId(), LocalDateTime.now());

        return sDocOutputBoundary.prepareSucessView(responseModel);
    }
}
