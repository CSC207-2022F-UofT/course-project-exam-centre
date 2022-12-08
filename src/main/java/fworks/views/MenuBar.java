package fworks.views;

import ia.controllers.LogoutController;
import ia.exceptions.LogoutFailed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Menu bar to display username and allow logout
 * @layer drivers and frameworks
 */
public class MenuBar extends JMenuBar implements ActionListener {

    // get this from login use case? hardcoded for now
    private LogoutController logoutController;
    private String userFullName = "First Last";
    private JMenu userMenu;
    private JMenuItem profileMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem logoutMenuItem;

    /**
     * Constructs an instance of the MenuBar with a controller for the Logout Use case
     * @param logOutController controller for the logout use case
     */
    public MenuBar(LogoutController logoutController) {
        this.logoutController = logoutController;
        logoutMenuItem = new JMenuItem("Sign out");
        logoutMenuItem.addActionListener(this);

        userMenu = new JMenu(userFullName);
        userMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        userMenu.add(logoutMenuItem);

//        additional options that can be implemented in the future
//        profileMenuItem = new JMenuItem("Profile");
//        settingsMenuItem= new JMenuItem("Settings");
//        profileMenuItem.addActionListener(this);
//        settingsMenuItem.addActionListener(this);
//        userMenu.add(profileMenuItem);
//        userMenu.add(settingsMenuItem);

        this.add(Box.createHorizontalGlue());
        this.add(userMenu);
    }

    public void setUserFullName(String userFullName){
        this.userFullName = userFullName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: trigger logout use case
        if(e.getActionCommand().equals(logoutMenuItem.getText())){
            try{
                logoutController.logOut();
            } catch(LogoutFailed exception){
                // TODO: generic error message hehe
            }


        }
    }
}
