package fworks.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The panel component for TestFrame
 */
public class TestToolbar extends JPanel implements ActionListener {
    private JComboBox comboBox;
    private JButton addCourseButton;
    private JButton uploadTestButton;
    private JButton takeTestButton;
    private JButton solutionsButton;

    public TestToolbar() {
        JPanel westPanel = createWestPanel();
        JPanel eastPanel = createEastPanel();

        setLayout(new BorderLayout());
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }

    private JPanel createWestPanel() {
        comboBox = createComboBox();
        addCourseButton = new JButton("Add course");
        uploadTestButton = new JButton("Upload test");

        comboBox.addActionListener(this);
        addCourseButton.addActionListener(this);
        uploadTestButton.addActionListener(this);

        comboBox.setPreferredSize(new Dimension(120, 30));
        addCourseButton.setPreferredSize(new Dimension(120, 30));
        uploadTestButton.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(comboBox);
        panel.add(addCourseButton);
        panel.add(uploadTestButton);

        return panel;
    }

    private JPanel createEastPanel() {
        takeTestButton = new JButton("Take test");
        solutionsButton = new JButton("Solutions");
        solutionsButton.setEnabled(true); // Note: This is false until the user writes a test but
        // I set it to true for testing purposes

        takeTestButton.addActionListener(this);
        solutionsButton.addActionListener(this);

        takeTestButton.setPreferredSize(new Dimension(120, 30));
        solutionsButton.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(takeTestButton);
        panel.add(solutionsButton);

        return panel;
    }

    // Dummy combo box for testing
    private JComboBox createComboBox() {
        String[] courses = {"CSC207", "CSC236", "CSC258", "CSC263"};
        return new JComboBox(courses);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == comboBox) {
            // TODO: Switch course
        } else if (actionEvent.getSource() == addCourseButton) {
            // TODO: Add course
        } else if (actionEvent.getSource() == uploadTestButton) {
            // TODO: Upload test
        } else if (actionEvent.getSource() == takeTestButton) {
            // TODO: Take test
        } else if (actionEvent.getSource() == solutionsButton) {
            new SolutionFrame(); // Create the solution window
        }
    }
}
