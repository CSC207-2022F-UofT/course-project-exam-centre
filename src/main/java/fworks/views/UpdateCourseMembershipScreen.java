package fworks.views;

import ia.controllers.UpdateCourseMembershipController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateCourseMembershipScreen extends JFrame implements ActionListener {

    /**
     * A list of all the labels and buttons on the screen for a dynamically formed list
     */
    HashMap<JLabel, JCheckBox> courseDisplay;

    /**
     * The ID of the user updating their course membership
     */
    String userId;

    /**
     * The map of course IDs and their names
     * CourseList contains the string with the course's ID, followed by its name
     */
    HashMap<String, List<Object>> courseList;

    /**
     * Button to submit course selection
     */
    JButton submit;

    /**
     * The UpdateCourseMembershipController
     */
    UpdateCourseMembershipController updateCourseMembershipController;

    /**
     * Creates a screen for adding courses to the user's membership
     * @param controller The controller for handling course addition
     */

    /**
     *  The viewmodel for this screen
     */
    CourseMembershipViewModel viewModel;

    /**
     * Creates a screen for updating a user's course membership
     * @param controller The controller for updating course membership
     * @param viewModel The view model for this screen
     */
    public UpdateCourseMembershipScreen(UpdateCourseMembershipController controller,
                                        CourseMembershipViewModel viewModel){
        this.updateCourseMembershipController = controller;
        this.viewModel = viewModel;
        submit = new JButton("Add Courses");
        submit.addActionListener(this);
    }

    /**
     * Creates the screen with the courses according to the view model
     * This screen auto-updates upon being called.
     */
    public void createScreen(){
        this.courseList = viewModel.getCourses();
        this.userId = viewModel.getUserID();
        reset();

        courseDisplay = new HashMap<>();

        this.setLayout(new GridLayout(courseList.size() + 1, 2));

        for(String ID: courseList.keySet()) {
            JLabel courseID = new JLabel((String) courseList.get(ID).get(0));
            JCheckBox courseSelect = new JCheckBox(ID);
            courseSelect.setSelected((boolean) courseList.get(ID).get(1));
            courseDisplay.put(courseID, courseSelect);
            this.add(courseSelect);
            this.add(courseID);
        }
        this.add(submit);
        this.pack();
        this.setVisible(true);
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
            StringBuilder courseDisplayString = new StringBuilder("\n");
            ArrayList<String> coursesToAdd = new ArrayList<>();
            for (JLabel courseID : courseDisplay.keySet()) {
                if (courseDisplay.get(courseID).isSelected()) {
                  coursesToAdd.add(courseID.getText());
                  courseDisplayString.append(courseID.getText()).append("\n");
                }
            }
            System.out.println(coursesToAdd);
            updateCourseMembershipController.updateCourseMembership(coursesToAdd);
            this.setVisible(false);
            JOptionPane.showMessageDialog(this, String.format("%s Successfully Uploaded", courseDisplayString));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Resets the view before being used again
     */
    private void reset() {
        this.remove(submit);
        for(JLabel courseID : courseDisplay.keySet()) {
            this.remove(courseID);
            this.remove(courseDisplay.get(courseID));
        }
        this.userId = null;
        this.setVisible(false);
    }
}