package com.example.way.finder.model;

public class LinkedCell {

    private final Cell currentCell;
    private final LinkedCell previousCell;
    private int distToFinish;
    private final int stepValue;
    private boolean isCalculated;

    public LinkedCell(Cell currentCell, LinkedCell prevCell, int distToFinish, int stepValue) {
        this(currentCell, prevCell, distToFinish, stepValue, false);
    }

    public LinkedCell(Cell currentCell, LinkedCell prevCell, int distToFinish, int stepValue, boolean isCalculated) {
        this.currentCell = currentCell;
        this.previousCell = prevCell;
        this.distToFinish = distToFinish;
        this.stepValue = stepValue;
        this.isCalculated = isCalculated;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public LinkedCell getPreviousCell() {
        return previousCell;
    }

    public int getDistToFinish() {
        return distToFinish;
    }

    public int getStepValue() {
        return stepValue;
    }

    public void setDistToFinish(int distToFinish) {
        this.distToFinish = distToFinish;
    }

    public int getAllDist() {
        return distToFinish + stepValue;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

    public void setCalculated(boolean calculated) {
        isCalculated = calculated;
    }
}
