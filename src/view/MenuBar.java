package view;

import javax.swing.*;

/**
 * The menu bar with the game and help menu.
 */
public class MenuBar extends JMenuBar {
    private JMenu gameMenu;
    private JMenu helpMenu;
    private JMenuItem newGame;
    private JMenuItem quit;
    private JMenuItem about;

    /**
     * Constructs a Menubar with a game and help menu.
     */
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

    /**
     * @return The new game button.
     */
    public JMenuItem getNewGame() {
        return newGame;
    }

    /**
     * @return The quit button.
     */
    public JMenuItem getQuit() {
        return quit;
    }

    /**
     * Adds actionlistener to the about-button.
     */
    private void addAboutListener() {
        about.addActionListener(e -> {
            String about = "Chess\n"
                    + "\u00A9 2020 Copyright\n"
                    + "Victor Lundgren victor.lundgren@outlook.com";
            JOptionPane.showMessageDialog(this, about, "About", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
