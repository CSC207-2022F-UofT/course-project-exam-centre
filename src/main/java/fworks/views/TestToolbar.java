package fworks.views;

import ia.controllers.SubmitTestDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The panel component for TestFrame
 */
public class TestToolbar extends JPanel implements ActionListener, Updatable {
    private JComboBox comboBox;
    private JButton addCourseButton;
    private JButton uploadTestButton;
    private JButton takeTestButton;
    private JButton solutionsButton;
    private SubmitTestDocController submitTestDocController;
    private UpdateCourseMembershipScreen updateCourseMembershipScreen;
    private MainViewModel mainViewModel;

    public TestToolbar(MainViewModel mvm, SubmitTestDocController stdc, UpdateCourseMembershipController ucmc) {
        this.submitTestDocController = stdc;
        this.mainViewModel = mvm;
        this.updateCourseMembershipScreen = new UpdateCourseMembershipScreen(ucmc, mainViewModel.getCourseMembershipViewModel());

        JPanel westPanel = createWestPanel();
        JPanel eastPanel = createEastPanel();

        setLayout(new BorderLayout());
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }

    private JPanel createWestPanel() {
        comboBox = createComboBox();
        addCourseButton = new JButton("Add course");
        uploadTestButton = new JButton("Upload test");

        comboBox.addActionListener(this);
        addCourseButton.addActionListener(this);
        uploadTestButton.addActionListener(this);

        comboBox.setPreferredSize(new Dimension(120, 30));
        addCourseButton.setPreferredSize(new Dimension(120, 30));
        uploadTestButton.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(comboBox);
        panel.add(addCourseButton);
        panel.add(uploadTestButton);

        return panel;
    }

    private JPanel createEastPanel() {
        takeTestButton = new JButton("Take test");
        solutionsButton = new JButton("Solutions");
        solutionsButton.setEnabled(false);

        takeTestButton.addActionListener(this);
        solutionsButton.addActionListener(this);

        takeTestButton.setPreferredSize(new Dimension(120, 30));
        solutionsButton.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(takeTestButton);
        panel.add(solutionsButton);

        return panel;
    }

    /**
     * Creates a JComboBox with the user's registered courses
     * @return A combo box with the user's courses
     */
    private JComboBox createComboBox() {
        ArrayList<String> courses = mainViewModel.getCourseMembershipViewModel().getCurrentCourses();
        return new JComboBox(courses.toArray());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == comboBox) {
            // TODO: Switch course
        } else if (actionEvent.getSource() == addCourseButton) {
            updateCourseMembershipScreen.createScreen();
        } else if (actionEvent.getSource() == uploadTestButton) {
            TestDocumentSubmissionScreen testDocumentSubmissionScreen = new TestDocumentSubmissionScreen(
                    submitTestDocController,
                    mainViewModel.getCurrentUserModel().getUserId(),
                    mainViewModel //get the current course
            );

        } else if (actionEvent.getSource() == takeTestButton) {
            // TODO: Take test
        } else if (actionEvent.getSource() == solutionsButton) {
            // TODO: Solutions
        }
    }

    @Override
    public void update() {
        comboBox = createComboBox();
        comboBox.addActionListener(this);


    }
}
