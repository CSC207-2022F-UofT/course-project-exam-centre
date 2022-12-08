package fworks.views;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing solutions
 */
public class SolutionFrame extends JFrame {
    public SolutionFrame() {
        super("Solutions");

        MenuBar menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        SolutionToolbar toolbar = new SolutionToolbar();
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
