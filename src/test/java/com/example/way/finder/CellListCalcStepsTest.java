package com.example.way.finder;

import com.example.way.finder.model.Cell;
import com.example.way.finder.service.WayFinder;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CellListCalcStepsTest {
    // 0:0 1:0 2:0 3:0
    // 0:1 1:1 2:1 3:1
    // 0:2 1:2 2:2 3:2
    // 0:3 1:3 2:3 3:3

    // 000 000 000 fin
    // sta 000 000 000
    // 000 000 000 000
    // 000 000 000 000

    // 030 020 010 000
    // 040 030 020 010
    // 050 040 030 020

    List<Cell> INIT_MAP = List.of(
            new Cell(0, 0),
            new Cell(1, 0),
            new Cell(2, 0, true),
            new Cell(0, 1),
            new Cell(1, 1),
            new Cell(2, 1, true),
            new Cell(0, 2),
            new Cell(1, 2),
            new Cell(2, 2, true),
            new Cell(3, 0),
            new Cell(3, 1),
            new Cell(3, 2),
            new Cell(3, 3),
            new Cell(0, 3),
            new Cell(1, 3),
            new Cell(2, 3)
    );

    @Test
    public void cellListCalcStepsTest() {
        WayFinder wayFinder = new WayFinder(INIT_MAP);
        List<Cell> way = wayFinder.getWay(
                new Cell(0, 1),
                new Cell(3, 0)
        );

        System.out.println("Way:");
        for (Cell cell : way) {
            System.out.println(cell.toString());
        }

    }
}
