package fworks.views;

import ia.controllers.CourseRegisterController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.CourseInfoSubViewModel;
import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class UpdateCourseMembershipScreen extends JFrame implements ActionListener {

    /**
     * A list of all the labels and buttons on the screen for a dynamically formed list
     */
    Map<JLabel, JCheckBox> courseDisplay;

    /**
     * The ID of the user updating their course membership
     */
    String userId;

    /**
     * The map of course IDs and their names
     * CourseList contains the string with the course's ID, followed by its name, then the user's status
     */
    Map<String, List<Object>> courseList;

    /**
     * Button to submit course selection
     */
    JButton submit;

    /**
     * Button to create a course
     */
    JButton addCourse;

    /**
     * The UpdateCourseMembershipController
     */
    UpdateCourseMembershipController updateCourseMembershipController;

    /**
     * Provides entry way to the course register use case
     */
    CourseRegisterController courseRegisterController;

    /**
     *  The viewmodel for this screen
     */
    MainViewModel mainViewModel;

    /**
     * A map containing all the course codes, and their corresponding ID
     */
    Map<String, String> courseCodeToId;

    /**
     * Creates a screen for updating a user's course membership
     * @param controller The controller for updating course membership
     * @param mainViewModel The main view model
     */
    public UpdateCourseMembershipScreen(UpdateCourseMembershipController controller,
                                        CourseRegisterController courseRegisterController,
                                        MainViewModel mainViewModel){
        this.updateCourseMembershipController = controller;
        this.mainViewModel = mainViewModel;
        this.courseRegisterController = courseRegisterController;

        submit = new JButton("Update Courses");
        addCourse = new JButton("Add Course");

        addCourse.addActionListener(this);
        submit.addActionListener(this);
    }

    /**
     * Creates the screen with the courses according to the view model
     * This screen auto-updates upon being called.
     */
    public void createScreen(){
        this.courseList = getParsedCourseList();
        this.userId = mainViewModel.getCurrentUserModel().getUserId();
        courseDisplay = new HashMap<>();
        reset();

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
        this.add(addCourse);
        this.pack();
        this.setVisible(true);
    }

    private Map<String, List<Object>> getParsedCourseList() {
        Map<String, List<Object>> returnList = new HashMap<String, List<Object>>();

        courseCodeToId = new HashMap<>();

        Set<String> userCourseList = mainViewModel.getCurrentUserCourseModels().keySet();
        Map<String, CourseInfoSubViewModel> allCourses = mainViewModel.getCourseInfoModels();

        for (String courseID : allCourses.keySet()) {
            courseCodeToId.put(allCourses.get(courseID).getCourseCode(), courseID);
            List<Object> courseData = new ArrayList<>();
            courseData.add(allCourses.get(courseID).getCourseName());
            courseData.add(userCourseList.contains(courseID));
            returnList.put(allCourses.get(courseID).getCourseCode(), courseData);
        }
        return returnList;
    }

    /**
     * Runs upon the user clicking the "submit" button. Once run, for each selected course, the registerCourse method on
     * the controller is called with the course ID and course name
     * @param event The user clicking the submit button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click: " + event);
        if(event.getActionCommand().equals(addCourse)) {
            new RegisterCoursePanel(courseRegisterController, mainViewModel);
        } else{
            try {
                StringBuilder courseDisplayString = new StringBuilder("\n");
                ArrayList<String> coursesToAdd = new ArrayList<>();
                for (JLabel courseID : courseDisplay.keySet()) {
                    if (courseDisplay.get(courseID).isSelected()) {
                        coursesToAdd.add(courseCodeToId.get(courseID.getText()));
                        courseDisplayString.append(courseID.getText()).append("\n");
                    }
                }
                updateCourseMembershipController.updateCourseMembership(coursesToAdd);
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, String.format("%s Courses Updated Successfully",
                        courseDisplayString));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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