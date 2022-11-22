package fworks.views;

import ia.controllers.UpdateCourseMembershipController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCourseMembershipScreen extends JPanel implements ActionListener {


    /**
     * The UpdateCourseMembershipController
     */
    UpdateCourseMembershipController updateCourseMembershipController;

    /**
     * Creates a screen for adding courses to the user's membership
     * @param controller The controller for handling course addition
     */

    public UpdateCourseMembershipScreen(UpdateCourseMembershipController controller) {
        this.updateCourseMembershipController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click: " + e);
    }
}
