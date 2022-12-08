package fworks.views;

import ia.controllers.DownloadDocController;
import ia.controllers.SubmitSolutionDocController;
import ia.viewmodels.CourseSubViewModel;
import ia.viewmodels.MainViewModel;
import ia.viewmodels.SolutionDocSubViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Set;

/**
 * The panel component for SolutionFrame
 * @layer drivers and frameworks
 */
public class SolutionToolbar extends JPanel implements ActionListener {
    DocumentView docView;
    DownloadDocController downloadDocController;
    private JButton discussionButton;
    private JButton uploadSolutionsButton;
    private JComboBox<String> chooseSolutionDoc;
    private SubmitSolutionDocController submitSolutionDocController;
    private MainViewModel mainViewModel;

    /** Construct an instance of the SolutionToolbar that takes in the DocumentView that it will update
     *
     * @param docView
     */
    public SolutionToolbar(DocumentView docView, MainViewModel mvm, SubmitSolutionDocController ssdc,
                           DownloadDocController downloadDocController) {
        this.docView = docView;
        this.submitSolutionDocController = ssdc;
        this.downloadDocController = downloadDocController;
        this.mainViewModel = mvm;


        Set<String> solutionSet = getSolutionMap().keySet();
        // Getting an array of all the solutionIds for the ComboBox
        String[] solutionList = (String[]) solutionSet.toArray();

        chooseSolutionDoc = new JComboBox<>(solutionList);
        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");



//        docView.setFilePath(solutionList[0]);

        discussionButton.addActionListener(this);
        uploadSolutionsButton.addActionListener(this);
        chooseSolutionDoc.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(discussionButton);
        add(uploadSolutionsButton);
        add(chooseSolutionDoc);


    }

    /**
     * Gets the map of the SolutionId and SolutionDocSubViewModel from the main view model
     * @return a mapping of the SolutionId to its subviewmodel
     */
    private Map<String, SolutionDocSubViewModel> getSolutionMap(){
        // Get the current data
        String currCourse = mainViewModel.getCurrentCourseId();
        String currTestId = mainViewModel.getCurrentTestId();

        // Getting the solutionIds
        Map<String, CourseSubViewModel> courses = mainViewModel.getCurrentUserCourseModels();

        return courses.get(currCourse).getTests().get(currTestId).getSolutionModels();
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
            String solutionId = action.getSelectedItem().toString();
            // Check if the Solution is downloaded, if it is, load the filepath
            if(mainViewModel.checkIfLocalDocumentPathExists(solutionId)){
                String filepath = mainViewModel.getLocalDocumentPath(solutionId);
                docView.setFilePath(filepath);
                docView.loadFile();
            // If not downloaded, try download the file and if not throw the error
            } else {
                downloadDocController.createInput(solutionId,
                            mainViewModel.getCurrentUserModel().getUserId());

                if(mainViewModel.checkIfLocalDocumentPathExists(solutionId)) {
                    String filepath = mainViewModel.getLocalDocumentPath(solutionId);
                    docView.setFilePath(filepath);
                    docView.loadFile();
                } else{
                    JOptionPane.showMessageDialog(this,
                            String.format("Unable to load %s", solutionId));
                }


            }
        }
    }
}
