package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StartView extends JFrame {
    private JFrame frame;
    private JButton startButton;
    private JButton quitButton;
    private JLabel title;

    public StartView() {
        // Create views swing UI components
        startButton = new JButton("start");
        quitButton = new JButton("Quit");
        title = new JLabel("Start Othello");
        // Create table model
        // Model model = new Model();
        // table.setModel(model);

        // Create controller
        // Controller controller = new Controller(searchTermTextField, model);
        // filterButton.addActionListener(controller);

        // Set the view layout
        JPanel ctrlPane = new JPanel();
        ctrlPane.setLayout(new BoxLayout(ctrlPane, BoxLayout.PAGE_AXIS));
        ctrlPane.add(title);
        ctrlPane.add(startButton);
        ctrlPane.add(quitButton);

        JPanel titlePane = new JPanel();
        titlePane.add(title);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlePane, ctrlPane);
        splitPane.setEnabled(false);

        // Display it all in a scrolling window and make the window appear
        JFrame frame = new JFrame("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ctrlPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(400,500);
    }
}
