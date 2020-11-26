package view;

import javax.swing.*;
import java.awt.*;

public class OthelloView {
    private JFrame frame;
    private JMenuBar mb;
    private GameScreen gameScreen;

    public final static int FRAME_WIDTH = 600;
    public final static int FRAME_HEIGH = 600;

    public OthelloView() {
        Dimension d = new Dimension(FRAME_WIDTH, FRAME_HEIGH);

        frame = new JFrame("Othello");
        frame.setSize(d);
        frame.setMinimumSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameScreen = new GameScreen();
        frame.add(gameScreen);

        mb = new JMenuBar();
        frame.setJMenuBar(mb);

        frame.pack();
    }

    /**
     * Shows the GUI.
     */
    public void show(){
        frame.setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }
}
