package fworks.views;

import javax.swing.*;

/**
 * Allows the presenter to update the view
 */
public interface ViewManagerGateway {

    /**
     * Updates the view with information from the viewmodel
     */
    void updateViews();

    /**
     * Creates a dialogue box to show the user an error
     * @param errorMessage The name of the error which occurred
     * @param presentationMessage A user-friendly name/description of the message
     */
    void showError(String errorMessage, String presentationMessage);
}
