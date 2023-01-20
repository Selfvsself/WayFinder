package com.example.way.finder.controller;

import com.example.way.finder.model.Cell;
import com.example.way.finder.model.WebCell;
import com.example.way.finder.service.WayFinder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MapController {

    @PostMapping("/map")
    public List<String> map(@RequestBody List<WebCell> map) {
        List<Cell> cells = map.stream()
                .map(Cell::new)
                .collect(Collectors.toList());
        Cell start = map.stream()
                .filter(WebCell::isStart)
                .map(Cell::new)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Start is not present"));
        Cell finish = map.stream()
                .filter(WebCell::isFinish)
                .map(Cell::new)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Finish is not present"));

        WayFinder wayFinder = new WayFinder(cells);
        List<Cell> way = wayFinder.getWay(start, finish);

        return way.stream().map(Cell::getId).collect(Collectors.toList());
    }
}
