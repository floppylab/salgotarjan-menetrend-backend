package com.floppylab.salgotarjanschedule.service;

import com.floppylab.salgotarjanschedule.domain.DayInfo;
import com.floppylab.salgotarjanschedule.domain.DayType;
import com.floppylab.salgotarjanschedule.repository.Days;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DayService {

    private final Days days;

    public List<DayType> calculateDayTypes() {
        LocalDate localDate = LocalDate.now(ZoneId.of("CET"));
        return calculateDayTypes(localDate);
    }

    public List<DayType> calculateDayTypes(LocalDate localDate) {

        if (localDate == null) {
            localDate = LocalDate.now(ZoneId.of("CET"));
        }
        if (localDate.isBefore(LocalDate.of(2020, 9, 1))) {
//                || localDate.isAfter(LocalDate.of(2020, 12, 12))) {
            return Collections.emptyList();
        }
        DayInfo info = getDayInfo(localDate);

        if (info.getDayType() == DayType.WORK_DAY) {
            return Arrays.asList(DayType.ALL_DAYS, DayType.WORK_DAY, DayType.WORK_DAY_AND_FREE_DAY);
        } else if (info.getDayType() == DayType.FREE_DAY) {
            return Arrays.asList(DayType.ALL_DAYS, DayType.FREE_DAY, DayType.WORK_DAY_AND_FREE_DAY, DayType.FREE_DAY_AND_DAY_OF_REST);
        } else { // info.getDayType() == DayType.DAY_OF_REST
            return Arrays.asList(DayType.ALL_DAYS, DayType.DAY_OF_REST, DayType.FREE_DAY_AND_DAY_OF_REST);
        }
    }

    public DayInfo getDayInfo(LocalDate date) {
        return this.days.getDays()
                .stream()
                .filter(dayInfo -> dayInfo.getDay().compareTo(date) == 0)
                .findFirst().get();
    }

}