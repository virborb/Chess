package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A transparent button.
 *
 */
public class TransparentButton extends JButton {
    private static final long serialVersionUID = 1L;
    private BufferedImage bg;

    public TransparentButton(int x,int y, int width, int height){
        super();
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBounds(x, y, width, height);
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

    public void setBackgroundImage(BufferedImage bg) {
        this.bg = bg;
        this.repaint();
    }
}
