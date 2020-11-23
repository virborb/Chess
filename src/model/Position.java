package model;

import java.util.Objects;

/*
The position on the board.
 */
public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * constructor which take an int array where
     * the first index is the row and the second
     * index is the column.
     * @param position An Array with the position.
     */
    public Position(int[] position) {
        this.row = position[0];
        this.col = position[1];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
