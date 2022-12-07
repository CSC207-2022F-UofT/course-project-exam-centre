package ia.presenters;

import fworks.views.ViewManagerGateway;
import ia.exceptions.LogoutFailed;
import uc.user.logout.LogoutOutputBoundary;

import javax.swing.text.View;

public class LogoutPresenter implements LogoutOutputBoundary {

    private final ViewManagerGateway viewManagerGateway;

    public LogoutPresenter(ViewManagerGateway viewManagerGateway) {
        this.viewManagerGateway = viewManagerGateway;
    }

    @Override
    public void prepareSuccessView() {
        // TODO: Update view model here
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // TODO: Update view model here
        viewManagerGateway.showError(errorMessage, "Logout Failed");
        throw new LogoutFailed(errorMessage);
    }
}

