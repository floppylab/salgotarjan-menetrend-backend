package com.floppylab.salgotarjanschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayInfo {

    private LocalDate day;

    private DayType dayType;

    private boolean summerTime;

    private boolean schoolHoliday;

}
