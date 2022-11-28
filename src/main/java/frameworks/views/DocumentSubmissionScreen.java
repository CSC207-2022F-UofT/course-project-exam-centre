package frameworks.views;

import iadapters.controllers.SubmitDocumentController;
import iadapters.controllers.TestDocController;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.io.File;
//import ia.controllers.SubmitSolutionDocController;

public class DocumentSubmissionScreen<T extends SubmitDocumentController> extends JPanel implements ActionListener {

    /**
     * Name of the test
     */
    JTextField name = new JTextField(15);

    /**
     * The difficulty of the test on a scale of 1-10
     */
    JTextField difficulty = new JTextField(2);

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
     * The Document Submission Controller
     */
    T docSubmissionController;

    /**
     * A window allowing the user to select a document for submission and set information of the test
     * @param controller The user document submission controller
     */
    public DocumentSubmissionScreen(T controller) {

        this.docSubmissionController = controller;

        //TODO: Fix alignment issues with large text box
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel nameLabel = new JLabel("Test Name:");
        JLabel duration = new JLabel("Test Duration");

        JButton selectFile = new JButton("Select File");
        JButton submit = new JButton("Submit Solution Document");

        selectFile.addActionListener(this);
        submit.addActionListener(this);

        this.add(fileName);
        this.add(selectFile);
        this.add(nameLabel);
        this.add(name);

        if (docSubmissionController instanceof TestDocController) {
            this.add(duration);
            this.add(durationField);
            this.add(new JLabel("Difficulty (1-10)"));
            this.add(difficulty);
            this.add(new JLabel("Description:"));
            this.add(description);
            submit.setText("Submit Test Document");
        }
        this.add(submit);
    }

    /**
     * Processess one of the button press events and gives a text output
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Select File")) {
            int r = fileChooser.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                fileName.setText(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println("File Selected with path: " + fileName.getText());
            }
        } else {
            System.out.println("Click: " + e.getActionCommand());
            //TODO: Figure out some way to submit other information related to the doc (difficulty, name, time)
            docSubmissionController.SubmitDocument(new File(fileName.getText()));
        }
    }

    /**
     * Used for testing layout
     */
    public DocumentSubmissionScreen() {
        JFrame application = new JFrame("Test Solutions Doc Screen");
        JPanel screens = new JPanel();
        application.add(screens);
        DocumentSubmissionScreen filescreen = new DocumentSubmissionScreen(new TestDocController());
        screens.add(filescreen, "test");
        application.pack();
        application.setVisible(true);
    }

}