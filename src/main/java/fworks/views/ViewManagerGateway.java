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
     * Creates a dialogue box displaying the error
     */
    void showError(String errorMessage, String presentationMessage);
}
