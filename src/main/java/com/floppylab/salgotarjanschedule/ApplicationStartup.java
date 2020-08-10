package com.floppylab.salgotarjanschedule;

import com.floppylab.salgotarjanschedule.repository.Lines;
import com.floppylab.salgotarjanschedule.repository.Stops;
import com.floppylab.salgotarjanschedule.repository.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final Lines lines;
    private final Stops stops;
    private final Schedule schedule;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info(lines.getLines().toString());
        log.info(stops.getStops().toString());
        log.info(schedule.getDepartures().toString());
    }
}
