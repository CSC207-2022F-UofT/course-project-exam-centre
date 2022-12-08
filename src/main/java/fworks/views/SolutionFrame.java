package fworks.views;

import ia.controllers.DownloadDocController;
import ia.controllers.LogoutController;
import ia.controllers.SubmitSolutionDocController;
import ia.controllers.UpdateCourseMembershipController;
import ia.viewmodels.MainViewModel;
import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing solutions
 * @layer drivers and frameworks
 */
public class SolutionFrame extends JFrame {

    private LogoutController logoutController;

    public SolutionFrame(MainViewModel mainViewModel,
                         SubmitSolutionDocController submitSolutionDocController,
                         LogoutController logoutController,
                         DownloadDocController downloadDocController) {
        super("Solutions");
        this.logoutController = logoutController;

        MenuBar menuBar = new MenuBar(mainViewModel, logoutController);
        this.setJMenuBar(menuBar);

        DocumentView documentView = new DocumentView(mainViewModel);
        SolutionToolbar toolbar = new SolutionToolbar(documentView,
                mainViewModel,
                submitSolutionDocController,
                downloadDocController);
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
