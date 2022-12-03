package fworks.views;

import ia.controllers.UpdateCourseMembershipController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCourseMembershipScreen extends JPanel implements ActionListener {

    /**
     * The textbox for searching for a course name or code
     */
    JTextField search = new JTextField(15);

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

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel searchLabel = new JLabel("Enter Course Name or Course Code");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        this.add(searchLabel);
        this.add(search);
        this.add(searchButton);
    }

    /**
     * Run on a button click event on the window.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO Run the controller's method to add the given course from search
        System.out.println("Click: " + e);
    }

    /**
     * Temporary main method for testing layout of screens
     * @param args args
     */
    public static void main(String[] args) {
        JFrame application = new JFrame("Test course search screen");
        JPanel screens = new JPanel();
        application.add(screens);
        UpdateCourseMembershipScreen ucms = new UpdateCourseMembershipScreen(new UpdateCourseMembershipController());
        screens.add(ucms, "test");
        application.pack();
        application.setVisible(true);
    }

}