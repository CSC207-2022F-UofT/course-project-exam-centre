package fworks.views;

import ia.controllers.SubmitSolutionDocController;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionDocumentSubmissionScreen extends JFrame implements ActionListener {

    /**
     * Name of the solution document
     */
    JTextField name = new JTextField(25);

    /**
     * The total marks for the related test
     */
    JTextField recordedScore = new JTextField(5);

    /**
     * The file path of the test document
     */
    JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Label to display the name of the file selected
     */
    JLabel fileName = new JLabel("No file selected");

    /**
     * The duration in hours of the test
     */
    JTextField durationHoursField = new JTextField(5);

    /**
     * The duration in minutes of the test
     */
    JTextField durationMinutesField = new JTextField(5);

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
     * This screen auto-displays when being created, should be disposed after use
     * @param controller The user document submission controller
     */
    public SolutionDocumentSubmissionScreen(SubmitSolutionDocController controller, String courseID, String parentTestID) {
        this.solutionDocController = controller;
        this.courseID = courseID;
        this.parentTestID = parentTestID;

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JButton selectFile = new JButton("Select File");
        JButton submit = new JButton("Submit Solution Document");

        selectFile.addActionListener(this);
        submit.addActionListener(this);

        // Initial Layout Setup
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 5, 5);

        // Line 1 Column 1
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        this.add(fileName, gridBagConstraints);

        // Line 2 Column 1
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        this.add(selectFile, gridBagConstraints);

        // Line 3 Column 1
        gridBagConstraints.gridy = 2;
        JLabel nameLabel = new JLabel("Test Name:");
        this.add(nameLabel, gridBagConstraints);

        // Line 3 Column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 4;
        this.add(name, gridBagConstraints);

        // Line 4 Column 1
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JLabel duration = new JLabel("Test Duration:");
        this.add(duration, gridBagConstraints);

        // Line 4 Column 2
        JLabel hours = new JLabel("Hours:");
        gridBagConstraints.gridx = 1;
        this.add(hours, gridBagConstraints);

        // Line 4 Column 3
        gridBagConstraints.gridx = 2;
        this.add(durationHoursField, gridBagConstraints);

        // Line 4 Column 4
        JLabel minutes = new JLabel("Minutes:");
        gridBagConstraints.gridx = 3;
        this.add(minutes, gridBagConstraints);

        // Line 4 Column 5
        gridBagConstraints.gridx = 4;
        this.add(durationMinutesField, gridBagConstraints);

        // Line 5 Column 1
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        this.add(new JLabel("Your Score:"), gridBagConstraints);

        // Line 5 Column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        this.add(recordedScore, gridBagConstraints);

        submit.setText("Submit Test Document");

        // Line 6 Column 1
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        this.add(submit, gridBagConstraints);

        this.pack();
        this.setVisible(true);
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
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                String shortFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
                fileName.setText(shortFileName);
                System.out.println("File Selected with path: " + fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else {
            try {
                System.out.println("Click: " + event.getActionCommand());
                Float timeParsedHours = Float.parseFloat(durationHoursField.getText())
                        + Float.parseFloat(durationMinutesField.getText())/60;
                solutionDocController.SubmitSolutionDoc(
                        name.getText(),
                        Float.parseFloat(recordedScore.getText()),
                        courseID,
                        timeParsedHours,
                        parentTestID,
                        fileName.getText()
                );
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, String.format("%s Successfully Uploaded", name.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}
