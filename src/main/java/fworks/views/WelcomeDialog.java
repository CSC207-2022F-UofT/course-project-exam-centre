package fworks.views;

import ia.controllers.LoginController;
import ia.controllers.LogoutController;
import ia.controllers.UserRegisterController;
import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A dialog for the user to register or log in
 */
public class WelcomeDialog extends JDialog implements ActionListener {
    private LogoutController logoutController;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private JRadioButton newUserRadioButton;
    private JRadioButton returningUserRadioButton;

    public WelcomeDialog(LoginController loginController,
                         UserRegisterController userRegisterController,
                         LogoutController logoutController,
                         MainViewModel mainViewModel) {
        loginPanel = new LoginPanel(loginController, mainViewModel);
        registerPanel = new RegisterPanel(userRegisterController, logoutController, mainViewModel);

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

    /**
     * @return a panel with two radio buttons: new user and returning user
     */
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
