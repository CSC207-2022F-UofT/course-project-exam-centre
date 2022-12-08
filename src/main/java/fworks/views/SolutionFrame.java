package fworks.views;

import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing solutions
 */
public class SolutionFrame extends JFrame implements Updatable {

    private MenuBar menuBar;

    private SolutionToolbar toolbar;

    private DocumentView documentView;

    public SolutionFrame(MainViewModel mainViewModel) {
        super("Solutions");

        menuBar = new MenuBar(mainViewModel);
        this.setJMenuBar(menuBar);

        toolbar = new SolutionToolbar(mainViewModel);
        documentView = new DocumentView(mainViewModel);
        documentView.loadFile();

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(documentView.getPanel(), BorderLayout.EAST);

        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Updates the panels which are part of this frame
     */
    @Override
    public void update() {
        menuBar.update();
        toolbar.update();
        documentView.update();
    }
}
