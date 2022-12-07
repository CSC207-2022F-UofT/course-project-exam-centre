package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.LogoutFailed;
import uc.user.logout.LogoutOutputBoundary;

public class LogoutPresenter implements LogoutOutputBoundary {
    @Override
    public void prepareSuccessView() {
        // TODO: Update view model here
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // TODO: Update view model here
        ViewManagerGateway.showError(errorMessage, "Logout Failed");
        throw new LogoutFailed(errorMessage);
    }
}

