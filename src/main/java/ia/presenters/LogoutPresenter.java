package ia.presenters;

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
        throw new LogoutFailed(errorMessage);
    }
}

