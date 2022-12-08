package fworks.views;

import ia.controllers.SubmitTestDocController;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;

public class TestDocumentSubmissionScreen extends JFrame implements ActionListener {

    /**
     * Name of the test
     */
    JTextField name = new JTextField(25);

    /**
     * A description of the test
     */
    JTextArea description = new JTextArea(5, 25);

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
     * The number of questions on the test
     */
    JTextField numQuestions = new JTextField(5);

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
     * This screen auto displays after being created, and should be disposed after use
     * @param controller The user document submission controller
     */
    public TestDocumentSubmissionScreen(SubmitTestDocController controller, String courseID) {
        this.testDocController = controller;
        this.courseID = courseID;


        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JButton selectFile = new JButton("Select File");
        JButton submit = new JButton("Submit Test Document");

        selectFile.addActionListener(this);
        submit.addActionListener(this);

        description.setLineWrap(true);
        JScrollPane descriptionBox = new JScrollPane(description);

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
        this.add(new JLabel("Question Count:"), gridBagConstraints);

        // Line 5 Column 2
        gridBagConstraints.gridx = 1;
        this.add(numQuestions, gridBagConstraints);

        // Line 6 Column 1
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        this.add(new JLabel("Description:"), gridBagConstraints);

        // Line 6 Column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 4;
        this.add(descriptionBox, gridBagConstraints);

        submit.setText("Submit Test Document");

        // Line 7 Column 1
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
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
                String shortenedFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
                fileName.setText(shortenedFileName);
                System.out.println("File Selected with path: " + fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else {
            try {
                System.out.println("Click: " + event.getActionCommand());
                Float timeParsedHours = Float.parseFloat(durationHoursField.getText())
                        + Float.parseFloat(durationMinutesField.getText())/60;
                testDocController.submitTestDocument(
                        name.getText(),
                        Integer.parseInt(numQuestions.getText()),
                        timeParsedHours,
                        description.getText(),
                        courseID,
                        fileChooser.getSelectedFile().getAbsolutePath()
                        );
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, String.format("%s Successfully Uploaded", name.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}