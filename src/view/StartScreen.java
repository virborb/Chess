package view;

import javax.swing.*;

public class StartScreen extends JPanel {
    private JFrame frame;
    private JButton startButton;
    private JButton quitButton;
    private JLabel title;

    public StartScreen() {
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
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(title);
        this.add(startButton);
        this.add(quitButton);
    }
}
