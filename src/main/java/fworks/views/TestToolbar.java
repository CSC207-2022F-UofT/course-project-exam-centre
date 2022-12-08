package fworks.views;

import ia.controllers.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The panel component for TestFrame
 * @layer drivers and frameworks
 */
public class TestToolbar extends JPanel implements ActionListener {
    private LogoutController logoutController;
    private DocumentView docView;
    private JComboBox<String> testComboBox;
    private JComboBox courseComboBox;
    private JButton addCourseButton;
    private JButton uploadTestButton;
    private JButton takeTestButton;
    private JButton solutionsButton;

    /**
     * Creates an instance of the TestToolbar with the docView that it will update
     * @param docView the docView that will be updated
     */
    public TestToolbar(DocumentView docView, LogoutController logoutController) {
        this.docView = docView;
        this.logoutController = logoutController;
        JPanel westPanel = createWestPanel();
        JPanel eastPanel = createEastPanel();

        setLayout(new BorderLayout());
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }

    private JPanel createWestPanel() {
        courseComboBox = createCourseComboBox();
        addCourseButton = new JButton("Add course");
        uploadTestButton = new JButton("Upload test");
        testComboBox = createTestComboBox();

        courseComboBox.addActionListener(this);
        addCourseButton.addActionListener(this);
        uploadTestButton.addActionListener(this);

        courseComboBox.setPreferredSize(new Dimension(120, 30));
        addCourseButton.setPreferredSize(new Dimension(120, 30));
        uploadTestButton.setPreferredSize(new Dimension(120, 30));
        testComboBox.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(courseComboBox);
        panel.add(addCourseButton);
        panel.add(uploadTestButton);
        panel.add(testComboBox);


        return panel;
    }



    private JPanel createEastPanel() {
        takeTestButton = new JButton("Take test");
        solutionsButton = new JButton("Solutions");
        solutionsButton.setEnabled(true); // Note: This is false until the user writes a test but
        // I set it to true for testing purposes

        takeTestButton.addActionListener(this);
        solutionsButton.addActionListener(this);
        testComboBox.addActionListener(this);

        takeTestButton.setPreferredSize(new Dimension(120, 30));
        solutionsButton.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(takeTestButton);
        panel.add(solutionsButton);

        return panel;
    }

    // Dummy combo box for testing
    private JComboBox createCourseComboBox() {
        String[] courses = {"CSC207", "CSC236", "CSC258", "CSC263"};
        return new JComboBox<>(courses);
    }

    private JComboBox<String> createTestComboBox() {
        String[] courses = {"Test1", "Test2", "Test3"};
        JComboBox testComboBox = new JComboBox<>(courses);
        docView.setFilePath(courses[0]);
        return testComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == courseComboBox) {
        } else if (actionEvent.getSource() == addCourseButton) {
            // TODO: Add course
        } else if (actionEvent.getSource() == uploadTestButton) {
            // TODO: Upload test
        } else if (actionEvent.getSource() == takeTestButton) {
            // TODO: Take test
        } else if (actionEvent.getSource() == solutionsButton) {
            new SolutionFrame(logoutController); // Create the solution window
        } else if (actionEvent.getSource() == testComboBox){
            JComboBox action = (JComboBox)actionEvent.getSource();
            String testName = action.getSelectedItem().toString();
            docView.setFilePath(testName);
            docView.loadFile();
        }
    }
}
