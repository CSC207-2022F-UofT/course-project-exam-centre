package fworks.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Menu bar to display username and allow logout
 */
public class MenuBar implements ActionListener {
    // get this from login use case... hardcoded for now
    private String userFullName = "First Last";
    private JMenuBar menuBar;
    private JMenu userMenu;
    private JMenuItem profileMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem logoutMenuItem;

    public MenuBar() {
        profileMenuItem = new JMenuItem("Profile");
        settingsMenuItem= new JMenuItem("Settings");
        logoutMenuItem = new JMenuItem("Sign out");
        profileMenuItem.addActionListener(this);
        settingsMenuItem.addActionListener(this);
        logoutMenuItem.addActionListener(this);

        userMenu = new JMenu();
        // make menu appear at top right corner
        userMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        userMenu.add(profileMenuItem);
        userMenu.add(settingsMenuItem);
        userMenu.add(logoutMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalGlue());

        menuBar.add(userMenu);
    }

    public void setUserFullName(String userFullName){
        this.userFullName = userFullName;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: trigger logout use case
    }
}
