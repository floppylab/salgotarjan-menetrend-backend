package com.floppylab.salgotarjanschedule.configuration;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlFileContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Map<String, String> RESOURCE_LOCATIONS = new HashMap<String, String>() {{
            put("stops", "classpath:stops.yml");
            put("lines", "classpath:lines.yml");
            put("departures", "classpath:schedule.yml");
        }};

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        RESOURCE_LOCATIONS.forEach((key, value) ->
                addYamlPropertiesToSpringEnvironment(applicationContext, key, value)
        );
    }

    private void addYamlPropertiesToSpringEnvironment(ConfigurableApplicationContext applicationContext, String resourceName, String resourceLocation) {
        try {
            Resource resource = applicationContext.getResource(resourceLocation);

            YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
            List<PropertySource<?>> yamlProperties = yamlPropertySourceLoader.load(resourceName, resource);
            applicationContext.getEnvironment().getPropertySources().addLast(yamlProperties.get(0));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load resource file", e);
        }
    }

}
