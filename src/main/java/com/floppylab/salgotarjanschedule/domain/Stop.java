package com.floppylab.salgotarjanschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stop {

    private long id;

    private String name;

    private double latitude;

    private double longitude;

    private double direction;

    private List<LineInfo> lines;

}
