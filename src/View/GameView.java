package View;

import javax.swing.*;
import java.awt.*;

public class GameView {
    GridLayout gridLayout;

    public GameView() {
        JPanel panel = new JPanel(new GridLayout(8,8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                panel.add(new JButton(String.valueOf(i) + String.valueOf(j)));
            }
        }

        // Display it all in a scrolling window and make the window appear
        JFrame frame = new JFrame("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(400,500);
    }
}
