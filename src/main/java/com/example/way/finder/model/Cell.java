package com.example.way.finder.model;

import java.util.Objects;

public class Cell {

    private final String id;
    private final int x;
    private final int y;
    private boolean stop;

    public Cell(int x, int y) {
        this(x + "_" + y, x, y, false);
    }

    public Cell(int x, int y, boolean stop) {
        this(x + "_" + y, x, y, stop);
    }

    public Cell(String id, int x, int y, boolean stop) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.stop = stop;
    }

    public Cell(WebCell webCell) {
        this(webCell.getId(), webCell.getX(), webCell.getY(), webCell.isStop());
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", stop=" + stop +
                '}';
    }
}
