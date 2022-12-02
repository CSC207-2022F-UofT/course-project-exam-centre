package fworks.views;

import entities.StateTracker;
import ia.controllers.LoginController;
import ia.controllers.UserRegisterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The dialog for users to register or log in
 */
public class WelcomeDialog extends JDialog implements ActionListener {
    private StateTracker stateTracker;
    private LoginController loginController;
    private UserRegisterController userRegisterController;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private JRadioButton newUserRadioButton;
    private JRadioButton returningUserRadioButton;

    public WelcomeDialog(StateTracker stateTracker, LoginController loginController,
                         UserRegisterController userRegisterController) {
        this.stateTracker = stateTracker;
        this.loginController = loginController;
        this.userRegisterController = userRegisterController;

        loginPanel = new LoginPanel(stateTracker, loginController);
        registerPanel = new RegisterPanel(stateTracker, userRegisterController);
        JPanel buttonsPanel = createButtonsPanel();

        setLayout(new BorderLayout());
        add(registerPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.NORTH);

        newUserRadioButton.setSelected(true);

        newUserRadioButton.addActionListener(this);
        returningUserRadioButton.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JRadioButton selected = (JRadioButton) actionEvent.getSource();
        if (selected == newUserRadioButton) {
            remove(loginPanel);
            add(registerPanel, BorderLayout.CENTER);
        } else if (selected == returningUserRadioButton) {
            remove(registerPanel);
            add(loginPanel, BorderLayout.CENTER);
        }
        revalidate();
        repaint();
    }
}
