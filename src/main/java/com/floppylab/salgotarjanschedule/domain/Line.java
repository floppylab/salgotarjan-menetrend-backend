package com.floppylab.salgotarjanschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Line {

    private long id;

    private String number;

    private String description;

    private List<StopDelay> stopDelays;

    public boolean stopsAt(long stopId) {
        return stopDelays
                .stream()
                .anyMatch(stopDelay -> stopDelay.getStop() == stopId);
    }

}
