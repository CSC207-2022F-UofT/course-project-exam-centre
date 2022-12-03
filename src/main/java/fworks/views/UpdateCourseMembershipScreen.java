package fworks.views;

import ia.controllers.UpdateCourseMembershipController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class UpdateCourseMembershipScreen extends JPanel implements ActionListener {

    /**
     * A list of all the labels and buttons on the screen for a dynamically formed list
     */
    HashMap<JLabel, JCheckBox> courseDisplay;

    /**
     * The map of course IDs and their names
     * CourseList contains the string with the course's ID, followed by its name
     */
    HashMap<String, String> courseList;

    /**
     * The UpdateCourseMembershipController
     */
    UpdateCourseMembershipController updateCourseMembershipController;

    /**
     * Creates a screen for adding courses to the user's membership
     * @param controller The controller for handling course addition
     */

    public UpdateCourseMembershipScreen(UpdateCourseMembershipController controller, HashMap<String, String> courseList){
        this.updateCourseMembershipController = controller;
        this.courseList = courseList;

        courseDisplay = new HashMap<JLabel, JCheckBox>();

        this.setLayout(new GridLayout(courseList.size() + 1, 2));

        JButton submit = new JButton("Add Courses");
        submit.addActionListener(this);

        for(String ID: courseList.keySet()) {
            JLabel courseID = new JLabel(courseList.get(ID));
            JCheckBox courseSelect = new JCheckBox(ID);
            courseDisplay.put(courseID, courseSelect);
            this.add(courseSelect);
            this.add(courseID);
        }

        this.add(submit);
    }

    /**
     * Runs upon the user clicking the "submit" button. Once run, for each selected course, the registerCourse method on
     * the controller is called with the course ID and course name
     * @param event The user clicking the submit button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click: " + event);
        try {
            for (JLabel courseID : courseDisplay.keySet()) {
                if (courseDisplay.get(courseID).isSelected()) {
                    updateCourseMembershipController.registerCourse(courseDisplay.get(courseID).getText(), courseID.getText());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}