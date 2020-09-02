package com.floppylab.salgotarjanschedule.domain;

public enum DayType {

    ALL_DAYS, // minden nap
    WORK_DAY, // munkanap
    FREE_DAY, // szabadnapok
    DAY_OF_REST, // munkaszuneti napok
    WORK_DAY_AND_FREE_DAY, // munka- es szabadnapok
    FREE_DAY_AND_DAY_OF_REST; // szabad- es munkaszuneti napok

}
