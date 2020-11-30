package view;

import controller.OthelloController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel {
    private BufferedImage bg;
    private GridLayout gridLayout;
    private TransparentButton[][] tiles;

    public GameScreen() {
        gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(3);
        gridLayout.setVgap(3);
        this.setLayout(gridLayout);
        tiles = new TransparentButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new TransparentButton(i,j,
                        OthelloController.TILE_WIDTH, OthelloController.TILE_HEIGHT);
                this.add(tiles[i][j]);
            }
        }
        //this.setBackground(Color.darkGray);
        this.setSize(603,603);
        bg = new BufferedImage(OthelloView.FRAME_WIDTH, OthelloView.FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Paints the GameScreen with an image as background.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bg, 0, 0, null);
    }

    public void setBackgroundImage(BufferedImage bg) {
        this.bg = bg;
    }

    public JButton[][] getTiles() {
        return tiles;
    }

    public void changeTileColor(int row, int col, BufferedImage bg) {
        tiles[row][col].setBackgroundImage(bg);
    }
}
