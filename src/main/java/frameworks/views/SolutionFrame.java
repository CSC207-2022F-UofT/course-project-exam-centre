package frameworks.views;

import iadapters.controllers.DownloadDocController;
import iadapters.controllers.LogoutController;
import iadapters.controllers.SubmitSolutionDocController;
import iadapters.viewmodels.MainViewModel;
import iadapters.viewmodels.Updatable;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for viewing solutions
 * @layer drivers and frameworks
 */
public class SolutionFrame extends JFrame implements Updatable {

    private LogoutController logoutController;

    private SolutionToolbar solutionToolbar;
    private DocumentView documentView;

    public SolutionFrame(MainViewModel mainViewModel,
                         SubmitSolutionDocController submitSolutionDocController,
                         LogoutController logoutController,
                         DownloadDocController downloadDocController) {
        super("Solutions");
        this.logoutController = logoutController;

        MenuBar menuBar = new MenuBar(mainViewModel, logoutController);
        this.setJMenuBar(menuBar);

        this.documentView = new DocumentView(mainViewModel);
        this.solutionToolbar = new SolutionToolbar(documentView,
                mainViewModel,
                submitSolutionDocController,
                downloadDocController);
        documentView.loadFile();

        setLayout(new BorderLayout());
        add(solutionToolbar, BorderLayout.NORTH);
        add(documentView.getPanel(), BorderLayout.EAST);

        setSize(1100, 800);
        setLocationRelativeTo(null);
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void update() {
        solutionToolbar.update();
    }
}
