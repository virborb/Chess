package view;

import controller.ChessController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The game screen which holds the background image and the button for all tiles.
 */
public class GameScreen extends JPanel {
    private BufferedImage bg;
    private GridLayout gridLayout;
    private ImageButton[][] tiles;

    /**
     * Constructs the gameScreen with a grid layout with all button and creates
     * a background image.
     */
    public GameScreen() {
        gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(3);
        gridLayout.setVgap(3);
        this.setLayout(gridLayout);
        tiles = new ImageButton[8][8];
        for (int i = 0; i < ChessController.ROWS; i++) {
            for (int j = 0; j < ChessController.COLUMNS; j++) {
                tiles[i][j] = new ImageButton(
                        ChessController.TILE_WIDTH, ChessController.TILE_HEIGHT);
                this.add(tiles[i][j]);
            }
        }
        this.setSize(603,603);
        bg = new BufferedImage(ChessView.FRAME_WIDTH, ChessView.FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Paints the GameScreen with an image as background.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bg, 0, 0, null);
    }

    /**
     * @param bg The background image to set on the gameScreen.
     */
    public void setBackgroundImage(BufferedImage bg) {
        this.bg = bg;
    }

    /**
     * @return An array with all the buttons
     */
    public JButton[][] getTiles() {
        return tiles;
    }

    /**
     * Changes the background image on the specified tile position.
     * @param row The row of the tile button.
     * @param col The column of the tile button.
     * @param bg The background to set.
     */
    public void changeTileBackground(int row, int col, BufferedImage bg) {
        tiles[row][col].setBackgroundImage(bg);
    }
}
