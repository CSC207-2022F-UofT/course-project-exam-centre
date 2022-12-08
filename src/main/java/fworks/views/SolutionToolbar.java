package fworks.views;

import ia.controllers.SubmitSolutionDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The panel component for SolutionFrame
 */
public class SolutionToolbar extends JPanel implements ActionListener {
    private JButton discussionButton;
    private JButton uploadSolutionsButton;
    private SubmitSolutionDocController submitSolutionDocController;
    private MainViewModel mainViewModel;

    public SolutionToolbar(MainViewModel mvm, SubmitSolutionDocController ssdc) {
        this.submitSolutionDocController = ssdc;
        this.mainViewModel = mvm;

        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");

        discussionButton.addActionListener(this);
        uploadSolutionsButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(discussionButton);
        add(uploadSolutionsButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked == discussionButton) {
            // TODO: Discussions
        } else if (clicked == uploadSolutionsButton) {
            SolutionDocumentSubmissionScreen solutionDocumentSubmissionScreen = new SolutionDocumentSubmissionScreen(
                    submitSolutionDocController,
                    mainViewModel.getCurrentCourseId(),
                    mainViewModel.getCurrentTestId()
            );
        }
    }
}
