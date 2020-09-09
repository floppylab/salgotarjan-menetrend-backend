package com.floppylab.salgotarjanschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopDeparture {

    private long line;

    private LocalTime time;

    private LocalTime departureTime;

}
