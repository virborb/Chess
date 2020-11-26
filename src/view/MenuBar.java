package view;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    private JMenu gameMenu;
    private JMenu helpMenu;
    private JMenuItem newGame;
    private JMenuItem quit;
    private JMenuItem about;

    public MenuBar() {
        super();
        gameMenu = new JMenu("Game");
        helpMenu = new JMenu("Help");

        newGame = new JMenuItem("New Game");
        quit = new JMenuItem("Exit");
        about = new JMenuItem("About");

        gameMenu.add(newGame);
        gameMenu.add(quit);
        helpMenu.add(about);

        addAboutListener();
        this.add(gameMenu);
        this.add(helpMenu);
    }

    public JMenuItem getNewGame() {
        return newGame;
    }

    public JMenuItem getQuit() {
        return quit;
    }

    /**
     * Adds actionlistener to the about-button.
     */
    private void addAboutListener() {
        about.addActionListener(e -> {
            String about = "Othello\n"
                    + "\u00A9 2020 Copyright\n"
                    + "Victor Lundgren victor.lundgren@outlook.com";
            JOptionPane.showMessageDialog(this, about, "About", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
