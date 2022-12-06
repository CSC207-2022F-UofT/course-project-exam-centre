package uc.doc.submittest;

/**
 * SubmitTDocInputBoundary contains the method for the interface adapter to access the use case's interactor
 * @layer Use cases
 */
public interface SubmitTDocInputBoundary {

    /**
     * Invokes the test document submission use case
     * @param requestModel A request model containing the required information for saving a test document
     * @return A response model containing the information to be passed back to the presenters
     */
    SubmitTDocResponseModel submitTestDoc(SubTDocRequestModel requestModel);

}

