package fworks.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The panel component for existing users to log in
 */
public class LoginPanel extends JPanel implements ActionListener {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton cancelButton;
    private JButton loginButton;

    public LoginPanel() {
        JPanel fieldsPanel = createFieldsPanel();
        JPanel buttonsPanel = createButtonsPanel();

        cancelButton.addActionListener(this);
        loginButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(fieldsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        setSize(300, 200);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return usernameTextField.getText();
    }

    /**
     * @return the password
     */
    public char[] getPassword() {
        return passwordField.getPassword();
    }

    private JPanel createFieldsPanel() {
        usernameTextField = new JTextField(15);
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
        panel.add(new JLabel("Enter username"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(usernameTextField, gridBagConstraints);

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
            // TODO: Cancel log-in
        } else if (clicked == loginButton) {
            // TODO: Process log-in
        }
    }
}
