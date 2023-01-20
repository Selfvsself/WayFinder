package com.example.way.finder.service;

import com.example.way.finder.model.Cell;
import com.example.way.finder.model.LinkedCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WayFinder {

    private Cell[][] cells;
    private List<LinkedCell> stepList;
    private Cell startCell;
    private Cell finishCell;
    private Random random = new Random();

    private int maxX = 0;
    private int maxY = 0;

    public WayFinder(List<Cell> cells) {
        for (Cell cell : cells) {
            if (cell.getX() > maxX) maxX = cell.getX();
            if (cell.getY() > maxY) maxY = cell.getY();
        }
        this.cells = new Cell[maxY + 1][maxX + 1];

        for (Cell cell : cells) {
            int x = cell.getX();
            int y = cell.getY();
            this.cells[y][x] = cell;
        }
    }

    public List<Cell> getWay(Cell startCell, Cell finishCell) {
        this.startCell = startCell;
        this.finishCell = finishCell;
        stepList = new ArrayList<>();
        List<Cell> result = new ArrayList<>();
        LinkedCell cell = calcWay(null);

        while (cell != null) {
            result.add(0, cell.getCurrentCell());
            cell = cell.getPreviousCell();
        }
        //result.add(0, startCell);

        return result;
    }

    private LinkedCell calcWay(LinkedCell lastCell) {
        if (lastCell != null) {
            return lastCell;
        }
        if (stepList.isEmpty()) {
            LinkedCell startLinkedCell = new LinkedCell(
                    startCell,
                    null,
                    calcDistToFinish(startCell),
                    0);
            addToStepList(startLinkedCell);
            lastCell = calcNextStep(startLinkedCell);
        } else {
            LinkedCell nextCell = getMinimalDistanceCell(stepList);
            lastCell = calcNextStep(nextCell);
        }
        return calcWay(lastCell);
    }

    private LinkedCell getMinimalDistanceCell(List<LinkedCell> cells) {
        List<LinkedCell> minCells = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        for (LinkedCell cell : cells) {
            if (!cell.isCalculated()) {
                if (cell.getAllDist() < minValue) {
                    minValue = cell.getAllDist();
                    minCells = new ArrayList<>();
                    minCells.add(cell);
                } else if (cell.getAllDist() == minValue) {
                    minCells.add(cell);
                }
            }
        }
        int index = 0;
        if (minCells.size() > 1) {
            index = random.nextInt(minCells.size());
        }
        if (minCells.size() < 1) {
            throw new RuntimeException("Way doesn't exist");
        }
        LinkedCell minCell = minCells.get(index);
        return minCell;
    }

    private LinkedCell calcNextStep(LinkedCell cell) {
        int x = cell.getCurrentCell().getX();
        int y = cell.getCurrentCell().getY();
        cell.setCalculated(true);

        LinkedCell upLinkedCell = calcStepForNextStep(x, y - 1, cell);
        if (checkIsFinishCell(upLinkedCell)) return upLinkedCell;

        LinkedCell rightLinkedCell = calcStepForNextStep(x + 1, y, cell);
        if (checkIsFinishCell(rightLinkedCell)) return rightLinkedCell;

        LinkedCell downLinkedCell = calcStepForNextStep(x, y + 1, cell);
        if (checkIsFinishCell(downLinkedCell)) return downLinkedCell;

        LinkedCell leftLinkedCell = calcStepForNextStep(x - 1, y, cell);
        if (checkIsFinishCell(leftLinkedCell)) return leftLinkedCell;

        return null;
    }

    public int calcDistToFinish(Cell cell) {
        int maxX = Math.max(cell.getX(), finishCell.getX());
        int minX = Math.min(cell.getX(), finishCell.getX());
        int distX = maxX - minX;
        distX *= 10;

        int maxY = Math.max(cell.getY(), finishCell.getY());
        int minY = Math.min(cell.getY(), finishCell.getY());
        int distY = maxY - minY;
        distY *= 10;

        return distX + distY;
    }

    private LinkedCell calcStepForNextStep(int x, int y, LinkedCell prevCell) {
        LinkedCell nextLinkedCell = null;
        if (y >= 0 && x <= maxX && y <= maxY && x >= 0) {
            int stepValue = prevCell.getStepValue();
            stepValue += 10;
            Cell nextCell = cells[y][x];
            nextLinkedCell = new LinkedCell(nextCell, prevCell, calcDistToFinish(nextCell), stepValue);
            addToStepList(nextLinkedCell);
        }
        return nextLinkedCell;
    }

    private boolean checkIsFinishCell(LinkedCell cell) {
        return cell != null && cell.getCurrentCell().equals(finishCell);
    }

    private void addToStepList(LinkedCell cell) {
        if (!cell.getCurrentCell().isStop()) {
            LinkedCell contains = getCellFromSteps(cell);
            if (contains != null) {
                cell.setCalculated(contains.isCalculated());
                int containsValue = contains.getStepValue();
                int cellValue = cell.getStepValue();
                if (cellValue < containsValue) {
                    stepList.remove(contains);
                    stepList.add(cell);
                }
            } else {
                stepList.add(cell);
            }
        }
    }

    private LinkedCell getCellFromSteps(LinkedCell cell1) {
        for (LinkedCell cell2 : stepList) {
            if (cell1.getCurrentCell().equals(cell2.getCurrentCell())) {
                return cell2;
            }
        }
        return null;
    }

}
