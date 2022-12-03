package fworks.views;

import ia.controllers.SubmitTestDocController;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;

public class TestDocumentSubmissionScreen extends JPanel implements ActionListener {

    /**
     * Name of the test
     */
    JTextField name = new JTextField(15);

    /**
     * A description of the test
     */
    JTextArea description = new JTextArea(5, 35);

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
     * The number of questions on the test
     */
    JTextField numQuestions = new JTextField(5);

    /**
     * The ID of the user using this window
     */
    String userID;

    /**
     * The ID of the course this window was created for
     */
    String courseID;

    /**
     * The Test Document Submission Controller
     */
    SubmitTestDocController testDocController;

    /**
     * A window allowing the user to select a document for submission and set information of the test
     * @param controller The user document submission controller
     */
    public TestDocumentSubmissionScreen(SubmitTestDocController controller, String courseID, String userID) {
        this.testDocController = controller;
        this.userID = userID;
        this.courseID = courseID;

        //TODO: Fix alignment issues with large text box
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel nameLabel = new JLabel("Test Name:");
        JLabel duration = new JLabel("Test Duration");

        JButton selectFile = new JButton("Select File");
        JButton submit = new JButton("Submit Test Document");

        selectFile.addActionListener(this);
        submit.addActionListener(this);

        this.add(fileName);
        this.add(selectFile);
        this.add(nameLabel);
        this.add(name);
        this.add(duration);
        this.add(durationField);
        this.add(new JLabel("The number of questions on the test:"));
        this.add(numQuestions);
        this.add(new JLabel("Description:"));
        this.add(description);
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
                testDocController.SubmitTestDocument(
                        name.getText(),
                        Integer.parseInt(numQuestions.getText()),
                        Float.parseFloat(durationField.getText()), //TODO: HIGH PRIORITY determine a standard format for inputting time and how to parse as a float.
                        description.getText(),
                        userID,
                        courseID,
                        fileName.getText()
                        );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}