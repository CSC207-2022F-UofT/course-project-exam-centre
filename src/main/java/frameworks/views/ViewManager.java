package frameworks.views;

import iadapters.controllers.UpdateStateController;
import iadapters.gateways.ViewManagerGateway;
import iadapters.viewmodels.Updatable;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A class for updating all views at once and displaying error dialogue boxes
 * @layer Frameworks and driver
 */
public class ViewManager implements ViewManagerGateway {

    private ArrayList<Updatable> updatableViews;
    private UpdateStateController updateStateController;

    @Override
    public void setUpdateStateController(UpdateStateController updateStateController) {
        this.updateStateController = updateStateController;
    }

    /**
     * Sets the list of updatableViews
     * @param updatableViews A list of Updatable type containing updtable views
     */
    public void setUpdatableViews(ArrayList<Updatable> updatableViews) {
        this.updatableViews = updatableViews;
    }

    /**
     * Updates every view in the updatableViews array
     */
    @Override
    public void updateViews() {
        for (Updatable view : updatableViews) {
            view.update();
        }
    }

    /**
     * Creates a dialogue box to show the user an error
     * @param errorMessage The name of the error which occurred
     * @param presentationMessage A user-friendly name/description of the message
     */
    @Override
    public void showError(String errorMessage, String presentationMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, presentationMessage, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void updateState() {
        this.updateStateController.updateState();
    }
}
