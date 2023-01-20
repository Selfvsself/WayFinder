package com.example.way.finder.model;

public class WebCell {

    private final String id;
    private final int x;
    private final int y;
    private final boolean start;
    private final boolean finish;
    private final boolean stop;

    public WebCell(String id, int x, int y, boolean start, boolean finish, boolean stop) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.start = start;
        this.finish = finish;
        this.stop = stop;
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

    public boolean isStart() {
        return start;
    }

    public boolean isFinish() {
        return finish;
    }

    public boolean isStop() {
        return stop;
    }
}
