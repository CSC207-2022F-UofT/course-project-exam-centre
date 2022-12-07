package fworks.views;
import org.icepdf.ri.common.*;
import org.icepdf.ri.util.*;

import javax.swing.*;
import java.util.prefs.Preferences;

public class SolutionSplitPane extends JFrame {
    JFrame frame = new JFrame("Storage");
    JList<String> list = new JList<>();
    DefaultListModel<String> model = new DefaultListModel<>();

    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    public SolutionSplitPane(){
        list.setModel(model);

        model.addElement("Hello");

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        splitPane.setRightComponent(new DocumentView().getPanel());
        add(splitPane);

        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
