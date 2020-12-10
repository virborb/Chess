package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A button with an image.
 */
public class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage bg;

    /**
     * Creates a new button with an image.
     * @param width
     * @param height
     */
    public ImagePanel(int width, int height){
        super();
        this.setOpaque(false);
        bg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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
     * @param bg The background image to set.
     */
    public void setBackgroundImage(BufferedImage bg) {
        this.bg = bg;
        this.repaint();
    }
}
