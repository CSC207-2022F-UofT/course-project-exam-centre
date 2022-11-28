package fworks.views;

import javax.swing.*;
import java.awt.*;

public class SolutionFrame extends JFrame {
    public SolutionFrame() {
        super("Solutions");
        SolutionToolbar toolbar = new SolutionToolbar();
        Document document = new Document();

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(document.getPanel(), BorderLayout.EAST);

        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
