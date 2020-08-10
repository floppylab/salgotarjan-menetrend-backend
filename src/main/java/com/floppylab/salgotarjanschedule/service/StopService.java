package com.floppylab.salgotarjanschedule.service;

import com.floppylab.salgotarjanschedule.domain.Stop;
import com.floppylab.salgotarjanschedule.exception.ItemNotFoundException;
import com.floppylab.salgotarjanschedule.repository.Stops;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StopService {

    private final Stops stops;

    private final LineService lineService;

    public List<Stop> getStops() {
        List<Stop> s = stops.getStops();
        for (Stop stop : s) {
            stop.setLines(lineService.getStoppingLines(stop.getId()));
        }
        return s;
    }

    public Stop getStop(Long id) {
        Optional<Stop> stop = stops.getStops().stream().filter(s -> s.getId() == id).findFirst();
        if (stop.isPresent()) {
            return stop.get();
        } else {
            throw new ItemNotFoundException("error.noStop");
        }
    }

    public List<Stop> getStops(double latitude, double longitude) {
        //        Optional<Stop> stop = stops.getStops().stream().filter(s -> s.getId() == id).findFirst();
        //        if (stop.isPresent()) {
        //            return stop.get();
        //        } else {
        //            throw new ItemNotFoundException("error.noStop");
        //        }
        return null;
    }

}