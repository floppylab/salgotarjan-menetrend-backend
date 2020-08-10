package com.floppylab.salgotarjanschedule.service;

import com.floppylab.salgotarjanschedule.domain.BuildInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BuildInformationService {

    private final BuildProperties buildProperties;

    public BuildInformation getBuildInformation() {
        return new BuildInformation(buildProperties.getVersion(), Date.from(buildProperties.getTime()));
    }

}