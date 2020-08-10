package com.floppylab.salgotarjanschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departure {

    private long line;

    private LocalTime time;

    private Day day;

}
