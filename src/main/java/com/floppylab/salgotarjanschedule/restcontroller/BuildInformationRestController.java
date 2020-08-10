package com.floppylab.salgotarjanschedule.restcontroller;

import com.floppylab.salgotarjanschedule.domain.BuildInformation;
import com.floppylab.salgotarjanschedule.service.BuildInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BuildInformationRestController {

    private final BuildInformationService buildInformationService;

    @GetMapping("/info")
    public BuildInformation getBuildInformation() {
        return buildInformationService.getBuildInformation();
    }

}
