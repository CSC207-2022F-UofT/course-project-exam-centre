package fworks.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The panel component for SolutionFrame
 */
public class SolutionToolbar extends JPanel implements ActionListener {
    private JButton discussionButton;
    private JButton uploadSolutionsButton;

    public SolutionToolbar() {
        discussionButton = new JButton("Discussions");
        uploadSolutionsButton = new JButton("Upload solution");

        discussionButton.addActionListener(this);
        uploadSolutionsButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(discussionButton);
        add(uploadSolutionsButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked == discussionButton) {
            // TODO: Discussions
        } else if (clicked == uploadSolutionsButton) {
            // TODO: Upload solution
        }
    }
}
