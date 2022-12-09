package ia.gateways;

import ia.controllers.UpdateStateController;
import ia.viewmodels.Updatable;

import java.util.ArrayList;

/**
 * Allows the presenter to update the view
 */
public interface ViewManagerGateway {

    /**
     * Updates the view with information from the viewmodel
     */
    void updateViews();

    /**
     * Loads the updatable views in the viewmanager
     */
    void setUpdatableViews(ArrayList<Updatable> updatableScreens);

    /**
     * Creates a dialogue box to show the user an error
     * @param errorMessage The name of the error which occurred
     * @param presentationMessage A user-friendly name/description of the message
     */
    void showError(String errorMessage, String presentationMessage);

    void setUpdateStateController(UpdateStateController updateStateController);
    void updateState();
}
