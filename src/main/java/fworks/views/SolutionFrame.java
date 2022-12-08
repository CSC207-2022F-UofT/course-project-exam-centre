package fworks.views;

import ia.controllers.LogoutController;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing solutions
 * @layer drivers and frameworks
 */
public class SolutionFrame extends JFrame {
    private LogoutController logoutController;

    /**
     * Creates an instance of the SolutionFrame
     * @param logoutController the controller for the logout use case
     */
    public SolutionFrame(LogoutController logoutController) {
        super("Solutions");
        this.logoutController = logoutController;

        MenuBar menuBar = new MenuBar(logoutController);
        this.setJMenuBar(menuBar);

        DocumentView documentView = new DocumentView();
        SolutionToolbar toolbar = new SolutionToolbar(documentView);
        documentView.loadFile();

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(documentView.getPanel(), BorderLayout.EAST);

        setSize(1100, 800);
        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
