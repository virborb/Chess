package view;

import javax.swing.*;

/**
 * A transparent button.
 *
 */
public class TransparentButton extends JButton {
    private static final long serialVersionUID = 1L;

    public TransparentButton(int x,int y, int width, int height){
        super();
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBounds(x, y, width, height);
        this.setOpaque(true);
    }
}
