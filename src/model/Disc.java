package model;

/**
 * a disc that hold it's position on the board and it's color.
 */
public class Disc {
    private Color color;
    private final Position position;

    /**
     * Constructs an empty Disc with the specified Position.
     * @param position The Position of the Disc.
     */
    public Disc(Position position) {
        this.position = position;
        this.color = Color.EMPTY;
    }

    /**
     * Constructs an disc with the specified Color and Position.
     * @param position The Position of the Disc
     * @param color The Color of the Disc
     */
    public Disc(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    /**
     * Flips the Disc from black to white or
     * white to black.
     */
    public void flipDisc() {
        if (color == Color.BLACK) {
            color = Color.WHITE;
        } else if (color == Color.WHITE) {
            color = Color.BLACK;
        }
    }

    /**
     *
     * @return The Color of the Disc
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color Sets the Color of the Disc
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return The Position of the Disc
     */
    public Position getPosition() {
        return position;
    }
}
