package fworks.views;

import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing tests
 */
public class TestFrame extends JFrame implements Updatable {

    private MenuBar menuBar;

    private TestToolbar toolbar;

    private DocumentView documentView;

    public TestFrame(MainViewModel mainViewModel) {
        super("Exam Centre");

        menuBar = new MenuBar(mainViewModel);
        this.setJMenuBar(menuBar);

        toolbar = new TestToolbar(mainViewModel);
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
     * Updates the view
     */
    @Override
    public void update() {
        menuBar.update();
        toolbar.update();
        documentView.update();
    }
}
