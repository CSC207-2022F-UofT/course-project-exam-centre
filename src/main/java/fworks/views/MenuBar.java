package fworks.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Menu bar to display username and allow logout
 */
public class MenuBar extends JMenuBar implements ActionListener {

    // get this from login use case? hardcoded for now
    private String userFullName = "First Last";
    private JMenu userMenu;
    private JMenuItem profileMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem logoutMenuItem;

    public MenuBar() {
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
    }
}
