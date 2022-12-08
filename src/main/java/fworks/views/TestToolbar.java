package fworks.views;

import ia.controllers.LogoutController;
import ia.controllers.SubmitSolutionDocController;
import ia.controllers.SubmitTestDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.MainViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The panel component for TestFrame
 * @layer drivers and frameworks
 */
public class TestToolbar extends JPanel implements ActionListener, Updatable {
    private LogoutController logoutController;
    private DocumentView docView;
    private JComboBox<String> testComboBox;
    private JComboBox courseComboBox;
    private JButton addCourseButton;
    private JButton uploadTestButton;
    private JButton takeTestButton;
    private JButton solutionsButton;
    private SubmitTestDocController submitTestDocController;
    private SubmitSolutionDocController submitSolutionDocController;
    private UpdateCourseMembershipScreen updateCourseMembershipScreen;
    private MainViewModel mainViewModel;

    /**
     * Creates an instance of the TestToolbar with the docView that it will update
     * @param docView the docView that will be updated
     */
    public TestToolbar(DocumentView docView,
                       MainViewModel mvm,
                       SubmitTestDocController stdc,
                       SubmitSolutionDocController ssdc,
                       UpdateCourseMembershipController ucmc,
                       LogoutController logoutController) {
        this.submitTestDocController = stdc;
        this.submitSolutionDocController = ssdc;
        this.mainViewModel = mvm;
        this.updateCourseMembershipScreen = new UpdateCourseMembershipScreen(ucmc, mainViewModel.getCourseMembershipViewModel());
        this.docView = docView;
        this.logoutController = logoutController;

        JPanel westPanel = createWestPanel();
        JPanel eastPanel = createEastPanel();

        setLayout(new BorderLayout());
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }

    private JPanel createWestPanel() {
        courseComboBox = createComboBox();
        addCourseButton = new JButton("Add course");
        uploadTestButton = new JButton("Upload test");
        testComboBox = createTestComboBox();

        courseComboBox.addActionListener(this);
        addCourseButton.addActionListener(this);
        uploadTestButton.addActionListener(this);

        courseComboBox.setPreferredSize(new Dimension(120, 30));
        addCourseButton.setPreferredSize(new Dimension(120, 30));
        uploadTestButton.setPreferredSize(new Dimension(120, 30));
        testComboBox.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(courseComboBox);
        panel.add(addCourseButton);
        panel.add(uploadTestButton);
        panel.add(testComboBox);


        return panel;
    }



    private JPanel createEastPanel() {
        takeTestButton = new JButton("Take test");
        solutionsButton = new JButton("Solutions");
        solutionsButton.setEnabled(true); // Note: This is false until the user writes a test but
        // I set it to true for testing purposes

        takeTestButton.addActionListener(this);
        solutionsButton.addActionListener(this);
        testComboBox.addActionListener(this);

        takeTestButton.setPreferredSize(new Dimension(120, 30));
        solutionsButton.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(takeTestButton);
        panel.add(solutionsButton);

        return panel;
    }

    private JComboBox<String> createTestComboBox() {
        String[] courses = {"Test1", "Test2", "Test3"};
        JComboBox testComboBox = new JComboBox<>(courses);
        docView.setFilePath(courses[0]);
        return testComboBox;
    }

    /**
     * Creates a JComboBox with the user's registered courses
     * @return A combo box with the user's courses
     */
    private JComboBox createComboBox() {
            ArrayList<String> courseList = mainViewModel.getCourseMembershipViewModel().getCurrentCourses();
            return new JComboBox(courseList.toArray());
        }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == courseComboBox) {
            // Add a course to user's membership
        } else if (actionEvent.getSource() == addCourseButton) {
            updateCourseMembershipScreen.createScreen();
            // Upload a test
        } else if (actionEvent.getSource() == uploadTestButton) {
            TestDocumentSubmissionScreen testDocumentSubmissionScreen = new TestDocumentSubmissionScreen(
                    submitTestDocController,
                    mainViewModel.getCurrentCourseId()
            );
            // Sit a test
        } else if (actionEvent.getSource() == takeTestButton) {
            // TODO: Take test
            // Get the solutions
        } else if (actionEvent.getSource() == solutionsButton) {
            new SolutionFrame(mainViewModel, submitSolutionDocController, logoutController); // Create the solution window
            //Switch file being viewed
        } else if (actionEvent.getSource() == testComboBox){
            JComboBox action = (JComboBox)actionEvent.getSource();
            String testName = action.getSelectedItem().toString();
            docView.setFilePath(testName);
            docView.loadFile();
        }
    }

    @Override
    public void update() {
        courseComboBox = createComboBox();
        courseComboBox.addActionListener(this);

    }
}
