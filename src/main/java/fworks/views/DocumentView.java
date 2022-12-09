package fworks.views;

import ia.viewmodels.MainViewModel;
import org.icepdf.ri.common.*;
import org.icepdf.ri.util.*;

import javax.swing.*;
import java.util.prefs.Preferences;

/**
 * The panel that renders and displays a PDF
 * @layer Frameworks and drivers
 */
public class DocumentView {
    private SwingController controller;
    private JPanel panel;
    private String filePath;

    private MainViewModel mainViewModel;

    /**
     * Creates a new screen for viewing the document
     * @param mvm The main ViewModel
     */
    public DocumentView(MainViewModel mvm) {
        this.mainViewModel = mvm;
        setPreferences();
        controller = new SwingController();
        SwingViewBuilder factory = new SwingViewBuilder(controller);
        panel = factory.buildViewerPanel();
        if (mvm.getCurrentTestId() != null) {
            this.filePath = mainViewModel.getLocalDocumentPath(mvm.getCurrentTestId());
        } else {
            this.filePath = "./lib/blank.pdf";
        }
    }

    /**
     * Initialize with a default file path for testing
     */
    public DocumentView() {

        setPreferences();
        controller = new SwingController();
        SwingViewBuilder factory = new SwingViewBuilder(controller);
        panel = factory.buildViewerPanel();
//        filePath = "src/data/Solution2.pdf";
    }

    /**
     * @return the panel component
     */
    public JPanel getPanel() {
        setViewProperties();
        return panel;
    }

    /**
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Set the file path
     * @param filePath the name of the file // Note: we may want to rename this?
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the file into the view
     */
    public void loadFile() {
        controller.openDocument(filePath);
    }

    /**
     * Set the preferences of the view
     */
    private void setPreferences() {
        Preferences preferences = Preferences.userNodeForPackage(ViewerPropertiesManager.class);
        preferences.putBoolean("application.viewerpreferences.hidemenubar", true);
        preferences.putBoolean("application.viewerpreferences.hidetoolbar", true);
        preferences.putBoolean("application.statusbar", false);
    }

    /**
     * Set the properties of the view
     */
    private void setViewProperties() {
        controller.setPageViewMode(2, false);
        controller.setPageFitMode(4, true);
        panel.setSize(700, 700);
    }
}
