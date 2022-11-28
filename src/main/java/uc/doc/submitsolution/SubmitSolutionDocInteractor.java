package uc.doc.submitsolution;

import entities.SolutionDocFactory;
import entities.SolutionDocument;

import java.time.LocalDateTime;

public class SubmitSolutionDocInteractor implements SubmitSDocInputBoundary{

    private SubSDocOutputBoundary sDocOutputBoundary;

    private SubSDocDsGateway sDocDsGateway;

    private SolutionDocFactory factory;

    public SubmitSolutionDocInteractor(SubSDocDsGateway sDocDsGateway,
                                       SubSDocOutputBoundary sDocOutputBoundary,
                                       SolutionDocFactory factory) {
        this.factory = factory;
        this.sDocDsGateway = sDocDsGateway;
        this.sDocOutputBoundary = sDocOutputBoundary;
    }

    @Override
    public SubSDocResponseModel submitSolutionDoc(SubSDocRequestModel model) {



        SolutionDocument document = factory.create(model.getName(), model.getId(), model.getCourse(),
                model.getUser(), model.getRecordedScore(), model.getRecordedTime(), model.getRootID());
        model.getCourse().getTest().addSolutionDoc(document);

        SubSDocResponseModel responseModel = new SubSDocResponseModel(document, LocalDateTime.now());

        return sDocOutputBoundary.prepareSucessView(responseModel);
    }
}
