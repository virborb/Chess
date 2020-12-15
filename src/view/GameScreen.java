package view;

import controller.ChessController;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The game screen which holds the background image and the button for all tiles.
 */
public class GameScreen extends JPanel {
    private BufferedImage bg;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gbc;
    private ImagePanel[][] tiles;

    /**
     * Constructs the gameScreen with a grid layout with all button and creates
     * a background image.
     */
    public GameScreen() {
        gridBagLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.setLayout(gridBagLayout);
        tiles = new ImagePanel[8][8];
        for (int row = 0; row < ChessController.ROWS; row++) {
            for (int col = 0; col < ChessController.COLUMNS; col++) {
                if(row >= 2 && row <= 5) {
                    tiles[row][col] = null;
                    JPanel panel = new JPanel();
                    panel.setOpaque(false);
                    addObjects(panel, col, row, 1, 1);
                    continue;
                }
                tiles[row][col] = new ImagePanel(
                        ChessController.TILE_WIDTH, ChessController.TILE_HEIGHT);
                addObjects(tiles[row][col], col, row, 1, 1);
            }
        }
        this.setSize(603,603);
        bg = new BufferedImage(ChessView.FRAME_WIDTH, ChessView.FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void addObjects(Component component, int gridx, int gridy, int gridwidth, int gridheight){

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;

        gridBagLayout.setConstraints(component, gbc);
        this.add(component);
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
        repaint();
    }

    /**
     * @return An array with all the buttons
     */
    public ImagePanel[][] getTiles() {
        return tiles;
    }

    public void moveTile(ImagePanel panel, Position newPosition) {
        this.remove(panel);
        addObjects(panel, newPosition.getCol(), newPosition.getRow(), 1, 1);
        revalidate();
        repaint();
    }

    public void removePanel(ImagePanel panel) {
        for (int row = 0; row < ChessController.ROWS; row++) {
            for (int col = 0; col < ChessController.COLUMNS; col++) {
                if(panel.equals(tiles[row][col])) {
                    this.remove(tiles[row][col]);
                    tiles[row][col] = null;
                    //JPanel newPanel = new JPanel();
                    //newPanel.setOpaque(false);
                    //addObjects(newPanel, col, row, 1, 1);
                    this.revalidate();
                    repaint();
                }
            }
        }
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
