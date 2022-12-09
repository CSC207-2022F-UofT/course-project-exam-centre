package frameworks.views;

import iadapters.controllers.CourseRegisterController;
import iadapters.exceptions.CourseRegisterFailed;
import iadapters.viewmodels.MainViewModel;
import iadapters.viewmodels.Updatable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel displaying a list of courses with checkboxes, allowing a user to signup/unsignup for courses
 * @layer Frameworks and driver
 */
public class RegisterCoursePanel extends JPanel implements ActionListener, Updatable {

    private JTextField courseCodeField;
    private JTextField courseNameField;
    private JButton createButton;
    private JButton cancelButton;
    private MainViewModel mainViewModel;
    private CourseRegisterController courseRegisterController;

    /**
     * Creates an instance of the RegisterCoursePanel which allows for the creation of a course
     * @param courseRegisterController provides an entry to the course Register use case
     * @param mainViewModel the main view model
     */
    RegisterCoursePanel(CourseRegisterController courseRegisterController, MainViewModel mainViewModel){
        this.courseRegisterController = courseRegisterController;
        this.mainViewModel = mainViewModel;

        JPanel fieldsPanel = createFieldsPanel();
        JPanel buttonsPanel = createButtonsPanel();

        createButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        setSize(300, 200);

    }

    /**
     *  Creates a panel containing the fields and their labels
     * @return a Jpanel containing the course name and code fields
     */
    private JPanel createFieldsPanel() {
        courseNameField = new JTextField(15);
        courseCodeField = new JTextField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;

        // Row 1, column 1
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Course Code"), gridBagConstraints);

        // Row 1, column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(courseCodeField, gridBagConstraints);

        // Row 2, column 1
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Course Name"), gridBagConstraints);

        // Row 2, column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(courseNameField, gridBagConstraints);

        return panel;
    }

    /**
     * Creates a panel with the buttons for creating and cancelling
     * @return a panel with two buttons: cancel and create
     */
    private JPanel createButtonsPanel() {
        cancelButton = new JButton("Cancel");
        createButton = new JButton("Create");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(cancelButton);
        panel.add(createButton);

        cancelButton.setPreferredSize(new Dimension(120, 30));
        createButton.setPreferredSize(new Dimension(120, 30));

        return panel;
    }

    /**
     * Returns if any of the fields are left blank
     * @return true iff any field is blank
     */
    private boolean isBlank() {
        String courseName = courseNameField.getText();
        String courseCode = courseCodeField.getText();
        return courseCode.isBlank() || courseName.isBlank();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals(createButton)){
            if(isBlank()){
                JOptionPane.showMessageDialog(this, "One or more fields is empty.");
            } else{
                try{
                    String courseName = courseNameField.getText();
                    String courseCode = courseCodeField.getText();
                    courseRegisterController.registerCourse(courseName, courseCode);
                } catch(CourseRegisterFailed exception){
                    JOptionPane.showMessageDialog(this, exception.getMessage());

                }
                // If successful hide the screen
                this.setVisible(false);

            }
        } else if(actionEvent.getActionCommand().equals(cancelButton)){
            // Close if cancelled
            this.setVisible(false);
        }

    }

    @Override
    public void update(){
        //TODO: update the main view model
    }
}
