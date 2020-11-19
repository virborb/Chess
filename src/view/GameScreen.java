package view;

import controller.OthelloController;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    GridLayout gridLayout;
    JButton[][] tiles;

    public GameScreen() {
        gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        this.setLayout(gridLayout);
        tiles = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new TransparentButton(i,j,
                        OthelloController.TILE_WIDTH, OthelloController.TILE_HEIGHT);
                this.add(tiles[i][j]);
            }
        }
        this.setBackground(Color.darkGray);
        this.setSize(600,600);
    }

    public JButton[][] getTiles() {
        return tiles;
    }

    public void changeTileColor(int row, int col, Color c) {
        tiles[row][col].setBackground(c);
    }
}
