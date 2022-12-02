package fworks.views;

import entities.StateTracker;
import ia.controllers.UserRegisterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

/**
 * The panel component for new users to register
 */
public class RegisterPanel extends JPanel implements ActionListener {
    private StateTracker stateTracker;
    private UserRegisterController controller;

    private JTextField emailTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton cancelButton;
    private JButton registerButton;

    public RegisterPanel(StateTracker stateTracker, UserRegisterController controller) {
        this.stateTracker = stateTracker;
        this.controller = controller;

        JPanel fieldsPanel = createFieldsPanel();
        JPanel buttonsPanel = createButtonsPanel();

        cancelButton.addActionListener(this);
        registerButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        setSize(300, 200);
    }

    private JPanel createFieldsPanel() {
        emailTextField = new JTextField(15);
        passwordField1 = new JPasswordField(15);
        passwordField2 = new JPasswordField(15);

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
        panel.add(passwordField1, gridBagConstraints);

        // Third row
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter password again"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(passwordField2, gridBagConstraints);

        return panel;
    }

    private JPanel createButtonsPanel() {
        cancelButton = new JButton("Cancel");
        registerButton = new JButton("Register");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(cancelButton);
        panel.add(registerButton);

        cancelButton.setPreferredSize(new Dimension(120, 30));
        registerButton.setPreferredSize(new Dimension(120, 30));

        return panel;
    }

    private boolean isBlank() {
        String email = emailTextField.getText();
        String password1 = new String(passwordField1.getPassword());
        String password2 = new String(passwordField2.getPassword());
        return email.isBlank() || password1.isBlank() || password2.isBlank();
    }

    private boolean isInvalidEmail() {
        String email = emailTextField.getText();
        return !Pattern.matches("^\\S+@\\S+\\.\\S$", email);
    }

    private boolean passwordIsNotEqual() {
        return passwordField1.getPassword() != passwordField2.getPassword();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked == cancelButton) {
            System.exit(0);
        } else if (clicked == registerButton) {
            if (isBlank()) {
                // TODO: blank field(s)
            } else if (isInvalidEmail()) {
                // TODO: invalid email
            } else if (passwordIsNotEqual()) {
                // TODO: non-matching password
            } else {
                String email = emailTextField.getText();
                String password = new String(passwordField1.getPassword());
                controller.create(email, password);
            }
        }
    }
}
