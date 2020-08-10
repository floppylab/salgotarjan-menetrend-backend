package com.floppylab.salgotarjanschedule.repository;

import com.floppylab.salgotarjanschedule.domain.Stop;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties
public class Stops {

    private List<Stop> stops;

}
