package com.floppylab.salgotarjanschedule.service;

import com.floppylab.salgotarjanschedule.domain.Line;
import com.floppylab.salgotarjanschedule.domain.LineInfo;
import com.floppylab.salgotarjanschedule.exception.ItemNotFoundException;
import com.floppylab.salgotarjanschedule.repository.Lines;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LineService {

    private final Lines lines;

    public List<Line> getLines() {
        return lines.getLines();
    }

    public Line getLine(Long id) {
        Optional<Line> line = lines.getLines().stream().filter(s -> s.getId() == id).findFirst();
        if (line.isPresent()) {
            return line.get();
        } else {
            throw new ItemNotFoundException("error.noLine");
        }
    }

    public List<LineInfo> getStoppingLines(Long stop) {
        return lines.getLines()
                .stream()
                .filter(line -> line.getStopDelays()
                        .stream()
                        .anyMatch(t -> t.getStop() == stop))
                .map(line -> new LineInfo(line.getId(), line.getNumber()))
                .collect(Collectors.toList());
    }

}