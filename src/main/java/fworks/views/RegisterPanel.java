package fworks.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPanel extends JPanel implements ActionListener {
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton cancelButton;
    private JButton registerButton;

    public RegisterPanel() {
        JPanel fieldsPanel = createFieldsPanel();
        JPanel buttonsPanel = createButtonsPanel();

        cancelButton.addActionListener(this);
        registerButton.addActionListener(this);

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
     * @return the first password
     */
    public char[] getPassword1() {
        return passwordField1.getPassword();
    }

    /**
     * @return the repeated password
     */
    public char[] getPassword2() {
        return passwordField2.getPassword();
    }

    /**
     * @return true iff the two passwords match
     */
    public boolean comparePassword() {
        return getPassword1() == getPassword2();
    }

    private JPanel createFieldsPanel() {
        usernameTextField = new JTextField(15);
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked == cancelButton) {
            // TODO: Cancel registration
        } else if (clicked == registerButton) {
            // TODO: Process registration
        }
    }
}
