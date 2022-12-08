package fworks.views;

import ia.viewmodels.MainViewModel;
import org.icepdf.ri.common.*;
import org.icepdf.ri.util.*;

import javax.swing.*;
import java.util.prefs.Preferences;

/**
 * The view that renders and displays a PDF
 * TODO: test with PDFs of different sizes
 */
public class DocumentView implements Updatable {
    private SwingController controller;
    private JPanel panel;
    private String filePath;

    private MainViewModel mainViewModel;

    /**
     * @param filePath the file path of the PDF
     */
    public DocumentView(MainViewModel mvm) {
        this.mainViewModel = mvm;
        //this.filePath get filepath from viewmodel
    }

    /**
     * Initialize with a default file path for testing
     */
    public DocumentView() {
        this.

        setPreferences();
        controller = new SwingController();
        SwingViewBuilder factory = new SwingViewBuilder(controller);
        panel = factory.buildViewerPanel();
        filePath = "/Users/takedakento/Downloads/project/test/csc207h-d21.pdf";
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
     * @param filePath
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

    private void setPreferences() {
        Preferences preferences = Preferences.userNodeForPackage(ViewerPropertiesManager.class);
        preferences.putBoolean("application.viewerpreferences.hidemenubar", true);
        preferences.putBoolean("application.viewerpreferences.hidetoolbar", true);
        preferences.putBoolean("application.statusbar", false);
    }

    private void setViewProperties() {
        controller.setPageViewMode(2, false);
        controller.setPageFitMode(4, true);
        panel.setSize(700, 700);
    }

    @Override
    public void update() {

    }
}
