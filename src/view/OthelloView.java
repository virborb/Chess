package view;

import javax.swing.*;
import java.awt.*;

public class OthelloView {
    private JFrame frame;
    private MenuBar mb;
    private GameScreen gameScreen;

    public final static int FRAME_WIDTH = 619;
    public final static int FRAME_HEIGHT = 665;

    public OthelloView() {
        Dimension d = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

        frame = new JFrame("Othello");
        frame.setSize(d);
        frame.setMinimumSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        gameScreen = new GameScreen();
        frame.add(gameScreen);

        mb = new MenuBar();
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

    public MenuBar getMenubar() {
        return mb;
    }
}
