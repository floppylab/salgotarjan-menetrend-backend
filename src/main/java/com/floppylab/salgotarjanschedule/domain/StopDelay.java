package com.floppylab.salgotarjanschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopDelay {

    private long stop;

    private int minutes;

}
