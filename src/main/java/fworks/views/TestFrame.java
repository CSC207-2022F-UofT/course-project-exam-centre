package fworks.views;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    public TestFrame() {
        super("Test");
        TestToolbar toolbar = new TestToolbar();
        DocumentView documentView = new DocumentView();

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(documentView.getPanel(), BorderLayout.EAST);

        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
