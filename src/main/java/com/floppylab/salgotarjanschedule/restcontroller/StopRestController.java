package com.floppylab.salgotarjanschedule.restcontroller;

import com.floppylab.salgotarjanschedule.domain.Stop;
import com.floppylab.salgotarjanschedule.service.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StopRestController {

    private final StopService stopService;

    /*--------------- GET ------------------*/

    /**
     * Finds all the stops.
     *
     * @return a list of stops
     */
    @GetMapping("/stops")
    public List<Stop> findStops() {
        return stopService.getStops();
    }

    /**
     * Finds a stop specified by its id.
     *
     * @param id id of a stop
     * @return a stop
     */
    @GetMapping("/stops/{id}")
    public Stop findStop(@PathVariable("id") Long id) {
        return stopService.getStop(id);
    }

}
