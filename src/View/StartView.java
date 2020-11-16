package View;

import javax.swing.*;
import java.awt.*;

public class StartView extends JFrame {
    private JFrame frame;
    private JButton start;
    private JButton quit;

    public StartView() {
        frame = new JFrame("Othello");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        start = new JButton("Start");
        quit = new JButton("Quit");

        // Add UI element to frame
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)..addComponent(start))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(quit));
    }
}
