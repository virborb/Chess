package view;

import javax.swing.*;

public class EndScreen extends JPanel {
    private JLabel winner;
    private JLabel whiteScore;
    private JLabel blackScore;
    private JButton newGame;
    private JButton quit;

    public EndScreen() {
        // Create views swing UI components
        winner = new JLabel();
        whiteScore = new JLabel("White Score: ");
        blackScore = new JLabel("Black score: ");
        newGame = new JButton("start new game");
        quit = new JButton("Quit");

        // Set the view layout
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(winner);
        this.add(whiteScore);
        this.add(blackScore);
        this.add(newGame);
        this.add(quit);
    }

    public void setBlackScore(int score) {
        blackScore.setText("black score: " + score);
    }

    public void setWhiteScore(int score) {
        whiteScore.setText("white's score: " + score);
    }

    public void setWinner(String winner) {
        this.winner.setText(winner);
    }
}
