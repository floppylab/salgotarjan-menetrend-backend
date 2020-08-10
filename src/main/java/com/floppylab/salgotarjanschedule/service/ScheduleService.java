package com.floppylab.salgotarjanschedule.service;

import com.floppylab.salgotarjanschedule.domain.Day;
import com.floppylab.salgotarjanschedule.domain.Departure;
import com.floppylab.salgotarjanschedule.domain.Line;
import com.floppylab.salgotarjanschedule.domain.LineInfo;
import com.floppylab.salgotarjanschedule.domain.StopDeparture;
import com.floppylab.salgotarjanschedule.repository.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final Schedule schedule;

    private final LineService lineService;

    private final DayTypeService dayTypeService;

    public List<Departure> findDepartures(Long line, LocalDate date) {
        // day types for today
        List<Day> possibleDays = dayTypeService.calculateDayTypes(date);

        return schedule.getDepartures()
                .stream()
                // that line on that day
                .filter(departure -> departure.getLine() == line && possibleDays.contains(departure.getDay()))
                // sort by stop times
                .sorted(Comparator.comparing(Departure::getTime))
                .collect(Collectors.toList());
    }

    public List<StopDeparture> findUpcomingDepartures(Long stop) {
        // lines that stop in that particular bus stop
        List<Long> stoppingLines = lineService.getStoppingLines(stop).stream().map(LineInfo::getId).collect(Collectors.toList());

        // day types for today
        List<Day> possibleDays = dayTypeService.calculateDayTypes();

        return schedule.getDepartures()
                .stream()
                // in that stop on that day
                .filter(departure -> stoppingLines.contains(departure.getLine()) && possibleDays.contains(departure.getDay()))
                // turn to stop departure
                .map(departure -> convertToStopDeparture(departure, stop))
                // filter possibly passed ones - 5 minutes delay
                .filter(stopDeparture -> stopDeparture.getTime().isAfter(LocalTime.now().minusMinutes(6)))
                // sort by stop times
                .sorted(Comparator.comparing(StopDeparture::getTime))
                .collect(Collectors.toList());
    }

    private StopDeparture convertToStopDeparture(Departure departure, Long stop) {
        StopDeparture stopDeparture = new StopDeparture();
        stopDeparture.setLine(departure.getLine());

        Line line = lineService.getLine(departure.getLine());
        int minutes = line.getStopDelays().stream().filter(t -> t.getStop() == stop).findFirst().get().getMinutes();
        stopDeparture.setTime(departure.getTime().plusMinutes(minutes));
        return stopDeparture;
    }

}