package fworks.views;

import ia.controllers.*;
import ia.exceptions.UserRegisterFailed;
import ia.viewmodels.MainViewModel;
import ia.viewmodels.Updatable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

/**
 * A panel for new users to register
 * @layer drivers and frameworks
 */
public class RegisterPanel extends JPanel implements ActionListener, Updatable {
    private UserRegisterController userRegisterController;
    private LogoutController logoutController;
    private JTextField firstNameField;
    private JTextField lastNameField;

    private JTextField emailTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton cancelButton;
    private JButton registerButton;
    private MainViewModel mainViewModel;
    private MainFrame mainFrame;
    private SubmitTestDocController submitTestDocController;
    private SubmitSolutionDocController submitSolutionDocController;
    private UpdateCourseMembershipController updateCourseMembershipController;
    private DownloadDocController downloadDocController;

    /**
     * Constructs a RegisterPanel with a controller
     * @param userRegisterController the controller for the register use case
     */
    public RegisterPanel(UserRegisterController userRegisterController,
                         LogoutController logoutController,
                         MainViewModel mainViewModel,
                         SubmitTestDocController submitTestDocController,
                         SubmitSolutionDocController submitSolutionDocController,
                         UpdateCourseMembershipController updateCourseMembershipController,
                         DownloadDocController downloadDocController) {
        this.userRegisterController = userRegisterController;
        this.logoutController = logoutController;
        this.mainViewModel = mainViewModel;
        this.submitSolutionDocController = submitSolutionDocController;
        this.submitTestDocController = submitTestDocController;
        this.updateCourseMembershipController = updateCourseMembershipController;
        this.downloadDocController = downloadDocController;

        this.mainFrame = null;

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
     * @return a panel with text fields
     */
    private JPanel createFieldsPanel() {
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        emailTextField = new JTextField(15);
        passwordField1 = new JPasswordField(15);
        passwordField2 = new JPasswordField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;

        // Row 1, column 0
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("First Name"), gridBagConstraints);
        // Row 1, column 1
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(firstNameField, gridBagConstraints);

        // Row 2, column 0
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Last Name"), gridBagConstraints);

        // Row 2, column 1
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(lastNameField, gridBagConstraints);

        // Row 2, column 1
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter email"), gridBagConstraints);

        // Row 2, column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(emailTextField, gridBagConstraints);

        // Row 3, column 1
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter password"), gridBagConstraints);

        // Row 3, column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(passwordField1, gridBagConstraints);

        // Row 4, column 1
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter password again"), gridBagConstraints);

        // Row 4, column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(passwordField2, gridBagConstraints);

        return panel;
    }

    /**
     * @return a panel with two buttons: cancel and register
     */
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

    /**
     * @return true iff any field is blank
     */
    private boolean isBlank() {
        String email = emailTextField.getText();
        String password1 = new String(passwordField1.getPassword());
        String password2 = new String(passwordField2.getPassword());
        return email.isBlank() || password1.isBlank() || password2.isBlank();
    }

    /**
     * @return true iff the email is valid
     */
    private boolean isValidEmail() {
        String email = emailTextField.getText();
        return Pattern.matches("^\\S+@\\S+\\.\\S$", email);
    }

    /**
     * @return true iff the two passwords match
     */
    private boolean passwordsMatch() {
        return passwordField1.getPassword() == passwordField2.getPassword();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked == cancelButton) {
            System.exit(0);
        } else if (clicked == registerButton) {
            if (isBlank()) {
                // TODO: blank field(s)
//            } else if (!isValidEmail()) {
//                // TODO: invalid email
            } else {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailTextField.getText();
                String password = new String(passwordField1.getPassword());
                String repeatPassword = new String(passwordField2.getPassword());

                try{
                    userRegisterController.registerUser(firstName, lastName, email, password, repeatPassword);
                } catch(UserRegisterFailed exception){
                // TODO: add popup that contains the error message
                }

                setVisible(false);

                //new TestFrame(logoutController); TODO fix this
            }
        }
    }

    @Override
    public void update() {
        if (mainViewModel.getCurrentUserModel() != null) {
            if(mainFrame == null) {
                if(mainViewModel.getCurrentUserCourseModels().isEmpty()) {
                    UpdateCourseMembershipScreen updateCourseMembershipScreen = new UpdateCourseMembershipScreen(
                            updateCourseMembershipController,
                            mainViewModel);
                    updateCourseMembershipScreen.createScreen();
                }
                mainFrame = new MainFrame(mainViewModel,
                        submitTestDocController,
                        submitSolutionDocController,
                        updateCourseMembershipController,
                        logoutController,
                        downloadDocController);
            } else {
                mainFrame.update();
            }
        } else {
            if(mainFrame != null) {
                mainFrame.setVisible(false);
                mainFrame.dispose();
                mainFrame = null;
            }
        }
    }
}
