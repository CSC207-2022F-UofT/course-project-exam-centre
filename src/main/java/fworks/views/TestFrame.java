package fworks.views;

import ia.controllers.SubmitSolutionDocController;
import ia.controllers.SubmitTestDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.MainViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing tests
 */
public class TestFrame extends JFrame {

    private MenuBar menuBar;

    private TestToolbar toolbar;

    private DocumentView documentView;

    public TestFrame(MainViewModel mainViewModel,
                     SubmitTestDocController submitTestDocController,
                     UpdateCourseMembershipController updateCourseMembershipController
                     ) {
        super("Exam Centre");

        menuBar = new MenuBar(mainViewModel);
        this.setJMenuBar(menuBar);

        toolbar = new TestToolbar(mainViewModel, submitTestDocController, updateCourseMembershipController);
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
}
