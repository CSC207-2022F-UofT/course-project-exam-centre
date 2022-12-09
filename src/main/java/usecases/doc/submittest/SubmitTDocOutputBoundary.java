package usecases.doc.submittest;

/**
 * SubmitTDocOutputBoundary provides methods for returning the status of the use case actions back to the presenters
 * @layer Use cases
 */
public interface SubmitTDocOutputBoundary {

        /**
         * Alerts the presenters that the request succeeded
         * @param message A response model containing the information to be passed to the presenters for a success
         * @return The data to be presented
         */
        SubmitTDocResponseModel prepareSuccessView(SubmitTDocResponseModel message);

        /**
         * Alerts the presenters that the request failed
         * @param errorMessage The error that occurred
         * @return The data to be presented
         */
        SubmitTDocResponseModel prepareFailView(String errorMessage);
}