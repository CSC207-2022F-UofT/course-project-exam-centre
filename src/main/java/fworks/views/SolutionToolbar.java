package fworks.views;

import ia.controllers.SubmitSolutionDocController;
import ia.viewmodels.CourseSubViewModel;
import ia.viewmodels.MainViewModel;
import ia.viewmodels.SolutionDocSubViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Map;

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
        this.submitSolutionDocController = ssdc;
        this.mainViewModel = mvm;

        List solutionList = new List();

        // TODO: Replace the solution list with a list of actual solutions available for the test
        // Get the current data
        String currCourse = mainViewModel.getCurrentCourseId();
        String currTestId = mainViewModel.getCurrentTestId();
        String currSolutionId = mainViewModel.getCurrentSolutionId();

        Map<String, CourseSubViewModel> courses = mainViewModel.getCurrentUserCourseModels();
        Map<String, SolutionDocSubViewModel> solutions =
                courses.get(currCourse).getTests().get(currTestId).getSolutionModels();

        for(SolutionDocSubViewModel i: solutions.values() ){
            solutionList.add(i.getSolutionName());

        //Update current test/solution first
            // First we get all the names, if click occurs check if its downloaded and if not download it.
            // If no solutions hide the view
        }

        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");
        chooseSolutionDoc = new JComboBox<String>(solutionList);
//        docView.setFilePath(solutionList[0]);

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
