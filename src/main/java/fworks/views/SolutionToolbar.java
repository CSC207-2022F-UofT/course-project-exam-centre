package fworks.views;

import javax.swing.*;
import java.awt.*;

public class SolutionToolbar extends JPanel {
    private JButton discussionButton;
    private JButton uploadSolutionsButton;

    public SolutionToolbar() {
        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(discussionButton);
        add(uploadSolutionsButton);
    }
}
