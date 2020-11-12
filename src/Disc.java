public class Disc {
    private Color color;
    private Position position;

    public Disc(Position position) {
        this.position = position;
        this.color = Color.EMPTY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
