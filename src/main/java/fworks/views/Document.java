package fworks.views;

import org.icepdf.ri.common.*;
import org.icepdf.ri.util.*;

import javax.swing.*;
import java.util.prefs.Preferences;

public class Document {
    private String filePath;
    private SwingController controller;
    private SwingViewBuilder factory;
    private JPanel panel;

    // Empty constructor for testing
    public Document() {
        filePath = "/Users/takedakento/Downloads/project/test/csc207h-d21.pdf";
        controller = new SwingController();
        factory = new SwingViewBuilder(controller);
        panel = factory.buildViewerPanel();
        panel.setSize(700, 700);
    }

    public JPanel getPanel() {
        setPreferences();
        controller.openDocument(filePath);
        controller.setPageViewMode(2, false);
        controller.setPageFitMode(4, true);
        return panel;
    }

    private void setPreferences() {
        Preferences preferences = Preferences.userNodeForPackage(ViewerPropertiesManager.class);
        preferences.putBoolean("application.viewerpreferences.hidemenubar", true);
        preferences.putBoolean("application.viewerpreferences.hidetoolbar", true);
        preferences.putBoolean("application.statusbar", false);
    }
}
