package fworks.views;

import ia.controllers.*;
import ia.viewmodels.MainViewModel;
import ia.viewmodels.Updatable;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing tests
 */
public class MainFrame extends JFrame implements Updatable {

    private TestToolbar testToolbar;
    private DocumentView documentView;

    private LogoutController logoutController;

    public MainFrame(MainViewModel mainViewModel,
                     SubmitTestDocController submitTestDocController,
                     SubmitSolutionDocController submitSolutionDocController,
                     UpdateCourseMembershipController updateCourseMembershipController,
                     LogoutController logoutController,
                     DownloadDocController downloadDocController
                     ) {
        super("Exam Centre");

        this.logoutController = logoutController;

        MenuBar menuBar = new MenuBar(mainViewModel, logoutController);
        this.setJMenuBar(menuBar);

        this.documentView = new DocumentView(mainViewModel);
        this.testToolbar = new TestToolbar(documentView,
                mainViewModel,
                submitTestDocController,
                submitSolutionDocController,
                updateCourseMembershipController,
                logoutController,
                downloadDocController);
        documentView.loadFile();

        setLayout(new BorderLayout());
        add(this.testToolbar, BorderLayout.NORTH);
        add(documentView.getPanel(), BorderLayout.EAST);

        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void update() {
        testToolbar.update();
    }
}
