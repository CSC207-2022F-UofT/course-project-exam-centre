package usecases.user.logout;

/**
 * LogoutOutputBoundary provides methods for the LogoutInteractor.
 * They should be implemented by a presenter.
 * @layer use cases
 */
public interface LogoutOutputBoundary {

    /**
     * Prepare a login view.
     */
    LogoutResponseModel prepareSuccessView(LogoutResponseModel responseModel);
    /**
     * Prepare a login view.
     */
    LogoutResponseModel prepareFailView(String errorMessage);
}
