package fworks.views;

import ia.controllers.DownloadDocController;
import ia.controllers.SubmitSolutionDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.CourseSubViewModel;
import ia.viewmodels.MainViewModel;
import ia.viewmodels.SolutionDocSubViewModel;
import ia.viewmodels.TestDocSubViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

/**
 * The panel component for SolutionFrame
 * @layer drivers and frameworks
 */
public class SolutionToolbar extends JPanel implements ActionListener, Updatable {
    DocumentView docView;
    private JButton discussionButton;

    private JButton uploadSolutionsButton;
    private JComboBox<String> chooseSolutionDoc;
    private SubmitSolutionDocController submitSolutionDocController;
    private DownloadDocController downloadDocController;
    private MainViewModel mainViewModel;

    /** Construct an instance of the SolutionToolbar that takes in the DocumentView that it will update
     *
     * @param docView
     */
    public SolutionToolbar(DocumentView docView,
                           MainViewModel mvm,
                           SubmitSolutionDocController ssdc,
                           DownloadDocController downloadDocController) {
        this.docView = docView;
        // TODO: Replace the solution list with a list of actual solutions available for the test
        this.submitSolutionDocController = ssdc;
        this.mainViewModel = mvm;
        this.downloadDocController = downloadDocController;

        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");
        chooseSolutionDoc = createSolutionList();

        discussionButton.addActionListener(this);
        uploadSolutionsButton.addActionListener(this);
        chooseSolutionDoc.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(discussionButton);
        add(uploadSolutionsButton);
        add(chooseSolutionDoc);
    }

    private JComboBox<String> createSolutionList() {
        Map<String, CourseSubViewModel> courseModels = mainViewModel.getCurrentUserCourseModels();
        Map<String, TestDocSubViewModel> testModles = courseModels.get(mainViewModel.getCurrentCourseId()).getTests();
        JComboBox testComboBox = new JComboBox<>(testModles.get(mainViewModel.getCurrentTestId())
                .getSolutionModels()
                .keySet()
                .toArray());
        return testComboBox;
    }

    private String getFilePath(String solutionID) {
        if(mainViewModel.checkIfLocalDocumentPathExists(solutionID)) {
            return mainViewModel.getLocalDocumentPath(solutionID);
        } else {
            try {
                downloadDocController.createInput(solutionID, mainViewModel.getCurrentUserModel().getUserId());
                if(mainViewModel.checkIfLocalDocumentPathExists(solutionID)) {
                    return mainViewModel.getLocalDocumentPath(solutionID);
                } else {
                    JOptionPane.showMessageDialog(this, String.format("Unable to download the document %s", solutionID));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(discussionButton.getText())) {
        // TODO: Discussions
        } else if (actionEvent.getActionCommand().equals(uploadSolutionsButton.getText())) {
            SolutionDocumentSubmissionScreen solutionDocumentSubmissionScreen = new SolutionDocumentSubmissionScreen(
                    submitSolutionDocController,
                    mainViewModel.getCurrentCourseId(),
                    mainViewModel.getCurrentTestId());
        }else if (actionEvent.getSource().equals(chooseSolutionDoc)){
            JComboBox action = (JComboBox)actionEvent.getSource();
            String solutionName = (String) action.getSelectedItem();
            docView.setFilePath(getFilePath(solutionName));
            docView.loadFile();
        }
    }

    /**
     * Updates the view
     */
    @Override
    public void update() {
        this.removeAll();
        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");
        chooseSolutionDoc = createSolutionList();

        discussionButton.addActionListener(this);
        uploadSolutionsButton.addActionListener(this);
        chooseSolutionDoc.addActionListener(this);

        add(discussionButton);
        add(uploadSolutionsButton);
        add(chooseSolutionDoc);
    }
}
