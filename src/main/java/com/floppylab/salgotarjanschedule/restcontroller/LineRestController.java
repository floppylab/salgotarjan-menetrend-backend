package com.floppylab.salgotarjanschedule.restcontroller;

import com.floppylab.salgotarjanschedule.domain.Line;
import com.floppylab.salgotarjanschedule.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LineRestController {

    private final LineService lineService;

    /*--------------- GET ------------------*/

    /**
     * Finds all the lines.
     *
     * @return a list of lines
     */
    @GetMapping("/lines")
    public List<Line> findLines() {
        return lineService.getLines();
    }

    /**
     * Finds a line specified by its id.
     *
     * @param id id of a line
     * @return a line
     */
    @GetMapping("/lines/{id}")
    public Line findLine(@PathVariable("id") Long id) {
        return lineService.getLine(id);
    }

}
