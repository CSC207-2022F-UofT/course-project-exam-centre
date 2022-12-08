package fworks.views;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing tests
 */
public class TestFrame extends JFrame {
    public TestFrame() {
        super("Exam Centre");

        MenuBar menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        TestToolbar toolbar = new TestToolbar();
        DocumentView documentView = new DocumentView();
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
