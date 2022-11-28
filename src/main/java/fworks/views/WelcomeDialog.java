package fworks.views;

import javax.swing.*;
import java.awt.*;

public class WelcomeDialog extends JDialog {
    private JRadioButton newUserRadioButton;
    private JRadioButton returningUserRadioButton;

    public WelcomeDialog() {
        JPanel buttonsPanel = createButtonsPanel();
        RegisterPanel registerPanel = new RegisterPanel();
        LoginPanel loginPanel = new LoginPanel();

        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.NORTH);
        add(registerPanel, BorderLayout.CENTER);

        newUserRadioButton.setSelected(true);

        newUserRadioButton.addActionListener(actionEvent -> {
            remove(loginPanel);
            add(registerPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        returningUserRadioButton.addActionListener(actionEvent -> {
            remove(registerPanel);
            add(loginPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createButtonsPanel() {
        newUserRadioButton = new JRadioButton("New user");
        returningUserRadioButton = new JRadioButton("Returning user");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(newUserRadioButton);
        buttonGroup.add(returningUserRadioButton);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(newUserRadioButton);
        panel.add(returningUserRadioButton);

        return panel;
    }
}
