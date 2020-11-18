package view;

import javax.swing.*;
import java.awt.*;

public class Screens extends JPanel {
    private static final long serialVersionUID = 1L;
    private StartScreen startScreen;
    private GameScreen gameScreen;
    private EndScreen endScreen;
    private CardLayout cardLayout;

    public final static String START = "start";
    public final static String GAME = "game";
    public final static String END = "end";

    public Screens () {

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        startScreen = new StartScreen();
        gameScreen = new GameScreen();
        endScreen = new EndScreen();

        this.add(START, startScreen);
        this.add(GAME, gameScreen);
        this.add(END, endScreen);

        cardLayout.addLayoutComponent(gameScreen, GAME);
        cardLayout.addLayoutComponent(startScreen, START);
        cardLayout.addLayoutComponent(endScreen, END);

        cardLayout.show(this, START);
    }

    /**
     * Shows the StartScreen.
     */
    public void showStartScreen(){
        cardLayout.show(this , START);
    }

    /**
     * Shows the GameScreen.
     */
    public void showGameScreen(){
        cardLayout.show(this, GAME);
    }

    /**
     * Shows the EndScreen.
     */
    public void showEndScreen(){
        cardLayout.show(this, END);
    }


}
