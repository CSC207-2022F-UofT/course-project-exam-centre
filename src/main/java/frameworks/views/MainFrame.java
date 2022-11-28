package frameworks.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JList courseList;
    private JList testList;
    private DefaultListModel courseListModel;
    private DefaultListModel testListModel;

    private JPanel testFrame;
    private JPanel solutionFrame;

    private JButton button;

    public MainFrame() {
        super("MainFrame");
        setLayout(new BorderLayout());

        courseListModel = new DefaultListModel();
        courseListModel.addElement("CSC207");
        courseListModel.addElement("CSC236");
        courseListModel.addElement("CSC258");

        courseList = new JList(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.setLayoutOrientation(JList.VERTICAL);
        courseList.setVisibleRowCount(-1);
        JScrollPane courseListScroller = new JScrollPane(courseList);

        testListModel = new DefaultListModel();
        testListModel.addElement("Test 1");
        testListModel.addElement("Test 2");
        testListModel.addElement("Test 3");
        testListModel.addElement("Test 4");
        testListModel.addElement("Test 5");

        testList = new JList(testListModel);
        testList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testList.setLayoutOrientation(JList.VERTICAL);
        testList.setVisibleRowCount(-1);
        JScrollPane testListScroller = new JScrollPane(testList);

        testFrame = new JPanel();
        solutionFrame = new JPanel();

        button = new JButton("Upload Test");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new DocumentSubmissionScreen<>();
            }
        });

        courseListScroller.setPreferredSize(new Dimension(100, 300));
        testListScroller.setPreferredSize(new Dimension(100, 300));
        testFrame.setPreferredSize(new Dimension(200, 300));
        solutionFrame.setPreferredSize(new Dimension(400, 300));

        add(courseListScroller, BorderLayout.WEST);
        add(testListScroller, BorderLayout.CENTER);
        // add(testFrame, BorderLayout.EAST);
        add(solutionFrame, BorderLayout.EAST);
        add(button, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
