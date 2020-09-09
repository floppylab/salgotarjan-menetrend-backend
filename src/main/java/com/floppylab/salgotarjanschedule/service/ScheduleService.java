package com.floppylab.salgotarjanschedule.service;

import com.floppylab.salgotarjanschedule.domain.DayInfo;
import com.floppylab.salgotarjanschedule.domain.DayType;
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

    private final DayService dayService;

    public List<Departure> findDepartures(Long line, LocalDate date) {
        // dayType types for today
        List<DayType> possibleDayTypes = dayService.calculateDayTypes(date);

        DayInfo dayInfo = dayService.getDayInfo(LocalDate.now());

        return schedule.getDepartures()
                .stream()
                // that line on that dayType
                .filter(departure -> departure.getLine() == line && possibleDayTypes.contains(departure.getDay()))
                .filter(departure -> filterSchoolWinterAndSummerExceptions(departure, dayInfo))
                // sort by stop times
                .sorted(Comparator.comparing(Departure::getTime))
                .collect(Collectors.toList());
    }

    public List<StopDeparture> findUpcomingDepartures(Long stop) {
        // lines that stop in that particular bus stop
        List<Long> stoppingLines = lineService.getStoppingLines(stop).stream().map(LineInfo::getId).collect(Collectors.toList());

        // dayType types for today
        List<DayType> possibleDayTypes = dayService.calculateDayTypes();

        DayInfo dayInfo = dayService.getDayInfo(LocalDate.now());

        return schedule.getDepartures()
                .stream()
                // in that stop on that dayType
                .filter(departure -> stoppingLines.contains(departure.getLine()) && possibleDayTypes.contains(departure.getDay()))
                .filter(departure -> filterSchoolWinterAndSummerExceptions(departure, dayInfo))
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
        stopDeparture.setDepartureTime(departure.getTime());

        Line line = lineService.getLine(departure.getLine());
        int minutes = line.getStopDelays().stream().filter(t -> t.getStop() == stop).findFirst().get().getMinutes();
        stopDeparture.setTime(departure.getTime().plusMinutes(minutes));
        return stopDeparture;
    }

    private boolean filterSchoolWinterAndSummerExceptions(Departure departure, DayInfo dayInfo) {
        if (departure.getOnlySummerTime() != null && departure.getOnlySummerTime() && !dayInfo.isSummerTime()) {
            return false;
        }
        if (departure.getOnlyWinterTime() != null && departure.getOnlyWinterTime() && dayInfo.isSummerTime()) {
            return false;
        }
        if (departure.getOnlySchoolHoliday() != null && departure.getOnlySchoolHoliday() && !dayInfo.isSchoolHoliday()) {
            return false;
        }
        if (departure.getOnlySchoolDay() != null && departure.getOnlySchoolDay() && dayInfo.isSchoolHoliday()) {
            return false;
        }
        return true;
    }

}