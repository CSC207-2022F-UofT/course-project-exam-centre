package frameworks.views;

import iadapters.controllers.*;
import iadapters.viewmodels.MainViewModel;
import iadapters.viewmodels.Updatable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A panel for existing users to log in
 * @layer Frameworks and driver
 */
public class LoginPanel extends JPanel implements ActionListener, Updatable {
    private LoginController controller;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton cancelButton;
    private JButton loginButton;
    private MainViewModel mainViewModel;
    private SubmitTestDocController submitTestDocController;
    private SubmitSolutionDocController submitSolutionDocController;
    private UpdateCourseMembershipController updateCourseMembershipController;
    private LogoutController logoutController;
    private DownloadDocController downloadDocController;
    private MainFrame mainFrame;
    private final UpdateStateController updateStateController;
    private CourseRegisterController courseRegisterController;

    public LoginPanel(LoginController controller,
                      MainViewModel mainViewModel,
                      SubmitTestDocController submitTestDocController,
                      SubmitSolutionDocController submitSolutionDocController,
                      UpdateCourseMembershipController updateCourseMembershipController,
                      LogoutController logoutController,
                      DownloadDocController downloadDocController,
                      UpdateStateController updateStateController,
                      CourseRegisterController courseRegisterController) {
        this.controller = controller;
        this.mainViewModel = mainViewModel;
        this.submitTestDocController = submitTestDocController;
        this.submitSolutionDocController = submitSolutionDocController;
        this.updateCourseMembershipController = updateCourseMembershipController;
        this.logoutController = logoutController;
        this.downloadDocController = downloadDocController;
        this.updateStateController = updateStateController;
        this.courseRegisterController = courseRegisterController;

        this.mainFrame = null;

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
     * @return a panel with text fields
     */
    private JPanel createFieldsPanel() {
        emailTextField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;

        // Row 1, column 1
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter email"), gridBagConstraints);

        // Row 1, column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(emailTextField, gridBagConstraints);

        // Row 2, column 1
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        panel.add(new JLabel("Enter password"), gridBagConstraints);

        // Row 2, column 2
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        panel.add(passwordField, gridBagConstraints);

        return panel;
    }

    /**
     * @return a panel with two buttons: cancel and log in
     */
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

    /**
     * @return true iff any field is blank
     */
    private boolean isBlank() {
        String email = emailTextField.getText();
        String password = new String(passwordField.getPassword());
        return email.isBlank() || password.isBlank();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked == cancelButton) {
            System.exit(0);
        } else if (clicked == loginButton) {
            if (isBlank()) {
                // TODO: blank field(s)
            } else {
                String email = emailTextField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    controller.logIn(email, password);
                    updateStateController.updateState();

                } catch (Exception exception) {
                    // TODO: handle error
                }
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
                            courseRegisterController,
                            mainViewModel);
                    updateCourseMembershipScreen.createScreen();
                }
                mainFrame = new MainFrame(mainViewModel,
                        submitTestDocController,
                        submitSolutionDocController,
                        updateCourseMembershipController,
                        logoutController,
                        downloadDocController,
                        courseRegisterController);
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
