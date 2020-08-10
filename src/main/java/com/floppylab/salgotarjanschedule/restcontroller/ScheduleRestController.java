package com.floppylab.salgotarjanschedule.restcontroller;

import com.floppylab.salgotarjanschedule.domain.Departure;
import com.floppylab.salgotarjanschedule.domain.StopDeparture;
import com.floppylab.salgotarjanschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleRestController {

    private final ScheduleService scheduleService;

    /*--------------- GET ------------------*/

    /**
     * Finds upcoming departures in a stop.
     *
     * @param stopId id of a stop
     * @return departures
     */
    @GetMapping("/stops/{stopId}/upcoming")
    public List<StopDeparture> findUpcomingDepartures(@PathVariable("stopId") Long stopId) {
        return scheduleService.findUpcomingDepartures(stopId);
    }

    /**
     * Finds departures of a line today.
     *
     * @param lineId id of a line
     * @return departures
     */
    @GetMapping("/lines/{lineId}/departures")
    public List<Departure> findDepartures(@PathVariable("lineId") Long lineId, @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleService.findDepartures(lineId, date);
    }

}
