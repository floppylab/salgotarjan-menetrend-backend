package com.floppylab.salgotarjanschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.print.Book;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departure {

    private long line;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    private DayType day;

    private Boolean onlySummerTime;

    private Boolean onlyWinterTime;

    private Boolean onlySchoolHoliday;

    private Boolean onlySchoolDay;

}
