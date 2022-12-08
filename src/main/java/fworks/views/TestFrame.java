package fworks.views;

import ia.controllers.LogoutController;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing tests
 */
public class TestFrame extends JFrame {
    private LogoutController logoutController;
    public TestFrame(LogoutController logoutController) {
        super("Exam Centre");

        this.logoutController = logoutController;

        MenuBar menuBar = new MenuBar(logoutController);
        this.setJMenuBar(menuBar);

        DocumentView documentView = new DocumentView();
        TestToolbar toolbar = new TestToolbar(documentView, logoutController);
        documentView.loadFile();

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(documentView.getPanel(), BorderLayout.EAST);

        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
