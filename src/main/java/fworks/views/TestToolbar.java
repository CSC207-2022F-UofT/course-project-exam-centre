package fworks.views;

import javax.swing.*;
import java.awt.*;

public class TestToolbar extends JPanel {
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
        solutionsButton.setEnabled(false);

        takeTestButton.setPreferredSize(new Dimension(120, 30));
        solutionsButton.setPreferredSize(new Dimension(120, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(takeTestButton);
        panel.add(solutionsButton);

        return panel;
    }

    // Empty constructor for testing
    private JComboBox createComboBox() {
        String[] courses = {"CSC207", "CSC236", "CSC258", "CSC263"};
        return new JComboBox(courses);
    }
}
