package fworks.views;

import ia.controllers.SubmitSolutionDocController;
import ia.controllers.SubmitTestDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.MainViewModel;
import ia.controllers.LogoutController;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing tests
 */
public class TestFrame extends JFrame {

    private LogoutController logoutController;

    public TestFrame(MainViewModel mainViewModel,
                     SubmitTestDocController submitTestDocController,
                     UpdateCourseMembershipController updateCourseMembershipController,
                     LogoutController logoutController
                     ) {
        super("Exam Centre");

        this.logoutController = logoutController;

        MenuBar menuBar = new MenuBar(mainViewModel, logoutController);
        this.setJMenuBar(menuBar);

        DocumentView documentView = new DocumentView(mainViewModel);
        TestToolbar toolbar = new TestToolbar(documentView,
                mainViewModel,
                submitTestDocController,
                updateCourseMembershipController,
                logoutController);
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
