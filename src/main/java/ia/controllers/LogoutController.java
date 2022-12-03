package ia.controllers;

import uc.user.logout.LogoutInputBoundary;

public class LogoutController {
    private final LogoutInputBoundary inputBoundary;

    public LogoutController(LogoutInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void logIn() {
        inputBoundary.logOut();
    }
}
