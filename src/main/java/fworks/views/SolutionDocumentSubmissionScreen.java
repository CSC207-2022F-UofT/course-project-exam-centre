package fworks.views;

import ia.controllers.SubmitSolutionDocController;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionDocumentSubmissionScreen extends JPanel implements ActionListener {

    /**
     * Name of the solution document
     */
    JTextField name = new JTextField(15);

    /**
     * The total marks for the related test
     */
    JTextField recordedScore = new JTextField(15);

    /**
     * The file path of the test document
     */
    JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Label to display the name of the file selected
     */
    JLabel fileName = new JLabel("No file selected");

    /**
     * The Duration of the test
     */
    JTextField durationField = new JTextField(15);

    /**
     * The ID of the user course this solutions document belongs to
     */
    String courseID;

    /**
     * The ID of the parent test to this document
     */
    String parentTestID;

    /**
     * The Test Document Submission Controller
     */
    SubmitSolutionDocController solutionDocController;

    /**
     * A window allowing the user to select a document for submission and set information of the test
     * @param controller The user document submission controller
     */
    public SolutionDocumentSubmissionScreen(SubmitSolutionDocController controller, String courseID, String parentTestID) {
        this.solutionDocController = controller;
        this.courseID = courseID;
        this.parentTestID = parentTestID;

        //TODO: Fix alignment issues with large text box
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel nameLabel = new JLabel("Document Name:");
        JLabel duration = new JLabel("Test Duration");

        JButton selectFile = new JButton("Select File");
        JButton submit = new JButton("Submit Solution Document");

        selectFile.addActionListener(this);
        submit.addActionListener(this);

        this.add(fileName);
        this.add(selectFile);
        this.add(nameLabel);
        this.add(name);
        this.add(duration);
        this.add(durationField);
        this.add(new JLabel("Total number of marks:"));
        this.add(recordedScore);

        submit.setText("Submit Test Document");
        this.add(submit);
    }

    /**
     * Processes one of the button press events and gives a text output
     * If the user clicks "Select File" a file choosing window is opened allowing them to select a document on their computer
     * If they click "Submit" then the information is submitted to the controller.
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Select File")) {
            int r = fileChooser.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                fileName.setText(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println("File Selected with path: " + fileName.getText());
            }
        } else {
            try {
                System.out.println("Click: " + event.getActionCommand());
                solutionDocController.SubmitSolutionDoc(
                        name.getText(),
                        Float.parseFloat(recordedScore.getText()),
                        courseID,
                        Float.parseFloat(durationField.getText()), //TODO: HIGH PRIORITY determine a standard format for inputting time and how to parse as a float.
                        parentTestID,
                        fileName.getText()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
