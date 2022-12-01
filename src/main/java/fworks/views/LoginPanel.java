package fworks.views;

import ia.controllers.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The panel component for existing users to log in
 */
public class LoginPanel extends JPanel implements ActionListener {
    private LoginController controller;

    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton cancelButton;
    private JButton loginButton;

    public LoginPanel(LoginController controller) {
        this.controller = controller;

        JPanel fieldsPanel = createFieldsPanel();
        JPanel buttonsPanel = createButtonsPanel();

        cancelButton.addActionListener(this);
        loginButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        setSize(300, 200);
    }

    private JPanel createFieldsPanel() {
        emailTextField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;

        // First row
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter email"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(emailTextField, gridBagConstraints);

        // Second row
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter password"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(passwordField, gridBagConstraints);

        return panel;
    }

    private JPanel createButtonsPanel() {
        cancelButton = new JButton("Cancel");
        loginButton = new JButton("Log in");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(cancelButton);
        panel.add(loginButton);

        cancelButton.setPreferredSize(new Dimension(120, 30));
        loginButton.setPreferredSize(new Dimension(120, 30));

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked == cancelButton) {
            System.exit(0);
        } else if (clicked == loginButton) {
            String email = emailTextField.getText();
            String password = new String(passwordField.getPassword());
            if (email.isBlank() || password.isBlank()) {
                // TODO
            } else {
                controller.logIn(email, password);
            }
        }
    }
}
