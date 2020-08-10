package com.floppylab.salgotarjanschedule.repository;

import com.floppylab.salgotarjanschedule.domain.Line;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties
public class Lines {

    private List<Line> lines;

}
