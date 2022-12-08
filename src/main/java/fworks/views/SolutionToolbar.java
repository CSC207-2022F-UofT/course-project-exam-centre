package fworks.views;

import ia.controllers.SubmitSolutionDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The panel component for SolutionFrame
 * @layer drivers and frameworks
 */
public class SolutionToolbar extends JPanel implements ActionListener {
    DocumentView docView;
    private JButton discussionButton;

    private JButton uploadSolutionsButton;
    private JComboBox<String> chooseSolutionDoc;
    private SubmitSolutionDocController submitSolutionDocController;
    private MainViewModel mainViewModel;

    /** Construct an instance of the SolutionToolbar that takes in the DocumentView that it will update
     *
     * @param docView
     */
    public SolutionToolbar(DocumentView docView, MainViewModel mvm, SubmitSolutionDocController ssdc) {
        this.docView = docView;
        String[] solutionList = {"Solution1", "Solution2", "Solution3"}; // Note: This is currently hard-coded
        // TODO: Replace the solution list with a list of actual solutions available for the test
        this.submitSolutionDocController = ssdc;
        this.mainViewModel = mvm;

        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");
        chooseSolutionDoc = new JComboBox<String>(solutionList);
        docView.setFilePath(solutionList[0]);

        discussionButton.addActionListener(this);
        uploadSolutionsButton.addActionListener(this);
        chooseSolutionDoc.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(discussionButton);
        add(uploadSolutionsButton);
        add(chooseSolutionDoc);
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
            docView.setFilePath(solutionName);
            docView.loadFile();
        }
    }
}
