package fworks.views;

import ia.controllers.*;
import ia.viewmodels.CourseSubViewModel;
import ia.viewmodels.MainViewModel;
import ia.viewmodels.TestDocSubViewModel;
import ia.viewmodels.Updatable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

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
    private DownloadDocController downloadDocController;
    private MainViewModel mainViewModel;
    private SolutionFrame solutionFrame;

    /**
     * Creates an instance of the TestToolbar with the docView that it will update
     * @param docView the docView that will be updated
     */
    public TestToolbar(DocumentView docView,
                       MainViewModel mvm,
                       SubmitTestDocController stdc,
                       SubmitSolutionDocController ssdc,
                       UpdateCourseMembershipController ucmc,
                       LogoutController logoutController,
                       DownloadDocController downloadDocController) {
        this.submitTestDocController = stdc;
        this.submitSolutionDocController = ssdc;
        this.mainViewModel = mvm;
        this.updateCourseMembershipScreen = new UpdateCourseMembershipScreen(ucmc, mainViewModel);
        this.docView = docView;
        this.logoutController = logoutController;
        this.downloadDocController = downloadDocController;

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
        Map<String, CourseSubViewModel> courseModels = mainViewModel.getCurrentUserCourseModels();
        Map<String, TestDocSubViewModel> testModles = courseModels.get(mainViewModel.getCurrentCourseId()).getTests();
        JComboBox testComboBox = new JComboBox<>(testModles.keySet().toArray());
        return testComboBox;
    }

    private String getFilePath(String testID) {
        if(mainViewModel.checkIfLocalDocumentPathExists(testID)) {
            return mainViewModel.getLocalDocumentPath(testID);
        } else {
            try {
                downloadDocController.createInput(testID, mainViewModel.getCurrentUserModel().getUserId());
                if(mainViewModel.checkIfLocalDocumentPathExists(testID)) {
                    return mainViewModel.getLocalDocumentPath(testID);
                } else {
                    JOptionPane.showMessageDialog(this, String.format("Unable to download the document %s", testID));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        return null;
    }

    /**
     * Creates a JComboBox with the user's registered courses
     * @return A combo box with the user's courses
     */
    private JComboBox createComboBox() {
            return new JComboBox(mainViewModel.getCurrentUserCourseModels().keySet().toArray());
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
            this.solutionFrame = new SolutionFrame(mainViewModel, submitSolutionDocController, logoutController, downloadDocController); // Create the solution window
            //Switch file being viewed
        } else if (actionEvent.getSource() == testComboBox){
            JComboBox action = (JComboBox)actionEvent.getSource();
            String testName = action.getSelectedItem().toString();
            docView.setFilePath(getFilePath(testName));
            docView.loadFile();
        }
    }

    @Override
    public void update() {
        this.removeAll();
        JPanel westPanel = createWestPanel();
        JPanel eastPanel = createEastPanel();
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);

        if(this.solutionFrame != null) {
            solutionFrame.update();
        }
    }
}
