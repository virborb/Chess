package view;

import javax.swing.*;
import java.awt.*;

/**
 * The main view for the game
 */
public class ChessView {
    private JFrame frame;
    private MenuBar mb;
    private GameScreen gameScreen;

    public final static int FRAME_WIDTH = 619;
    public final static int FRAME_HEIGHT = 665;

    /**
     * Make a new frame with specified dimensions and
     * adds the GameScreen and MenuBar.
     */
    public ChessView() {
        Dimension d = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

        frame = new JFrame("Chess");
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

    /**
     * Show a message dialog popup.
     * @param message The message to show.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * @return The GameScreen
     */
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * @return The MenuBar.
     */
    public MenuBar getMenubar() {
        return mb;
    }
}
