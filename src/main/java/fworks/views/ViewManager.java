package fworks.views;

import javax.swing.*;
import java.util.ArrayList;

public class ViewManager implements ViewManagerGateway {

    ArrayList<Updatable> updatableViews;

    /**
     * Creates a new ViewManager instance to manage updating the views
     * @param updatableViews
     */
    public ViewManager(ArrayList<Updatable> updatableViews) {
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
}
