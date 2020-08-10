package com.floppylab.salgotarjanschedule.service;

import com.floppylab.salgotarjanschedule.domain.Day;
import com.floppylab.salgotarjanschedule.domain.Departure;
import com.floppylab.salgotarjanschedule.domain.Line;
import com.floppylab.salgotarjanschedule.domain.StopDeparture;
import com.floppylab.salgotarjanschedule.repository.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayTypeService {

    public List<Day> calculateDayTypes() {
        LocalDate localDate = LocalDate.now(ZoneId.of("CET"));
        return  calculateDayTypes(localDate);
    }

    public List<Day> calculateDayTypes(LocalDate localDate) {

        // todo special days

        if (localDate == null) {
            localDate = LocalDate.now(ZoneId.of("CET"));
        }
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

        if (dayOfWeek.getValue() <= 5) {
            return Arrays.asList(Day.ALL_DAYS, Day.WORK_DAY, Day.WORK_DAY_AND_FREE_DAY);
        } else if (dayOfWeek.getValue() == 6) {
            return Arrays.asList(Day.ALL_DAYS, Day.FREE_DAY, Day.WORK_DAY_AND_FREE_DAY, Day.FREE_DAY_AND_DAY_OF_REST);
        } else { // dayOfWeek.getValue() == 7
            return Arrays.asList(Day.ALL_DAYS, Day.DAY_OF_REST, Day.FREE_DAY_AND_DAY_OF_REST);
        }
    }

}