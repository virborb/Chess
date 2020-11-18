package model;

public class Disc {
    private Color color;
    private final Position position;

    public Disc(Position position) {
        this.position = position;
        this.color = Color.EMPTY;
    }

    /**
     * Flips a disc
     */
    public void flipDisc() {
        if (color == Color.BLACK) {
            color = Color.WHITE;
        } else if (color == Color.WHITE) {
            color = Color.BLACK;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }
}
