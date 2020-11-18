package view;

import controller.Othello;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    GridLayout gridLayout;

    public GameScreen() {
        gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        this.setLayout(gridLayout);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new TransparentButton(i,j, Othello.TILE_WIDTH, Othello.TILE_HEIGHT);
                button.addActionListener(e -> {
                    System.exit(0);
                });
                this.add(button);
            }
        }
        this.setBackground(Color.darkGray);
        this.setSize(600,600);

    }
}
