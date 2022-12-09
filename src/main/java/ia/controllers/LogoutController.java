package ia.controllers;

import uc.user.logout.LogoutInputBoundary;

/**
 * LogoutController provides an entry way into the Logout use case
 * @layer interface adapters
 */
public class LogoutController {
    private final LogoutInputBoundary inputBoundary;

    /**
     * Creates an instance of DownloadDocController with an input boundary
     * @param inputBoundary provides methods to invoke use case
     */
    public LogoutController(LogoutInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Invokes the logout use case
     */
    public void logOut() {
        inputBoundary.logOut();
    }
}
