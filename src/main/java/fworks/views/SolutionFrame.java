package fworks.views;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing solutions
 * @layer drivers and frameworks
 */
public class SolutionFrame extends JFrame {
    /**
     * Creates an instance of the SolutionFrame
     */
    public SolutionFrame() {
        super("Solutions");

        MenuBar menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        DocumentView documentView = new DocumentView();
        SolutionToolbar toolbar = new SolutionToolbar(documentView);
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
