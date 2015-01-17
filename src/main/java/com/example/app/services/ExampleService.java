package com.example.app.services;

import com.google.common.util.concurrent.AbstractIdleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleService extends AbstractIdleService { 

    @Override
    protected void startUp() throws Exception {
        log.debug("ExampleService starting...");
    }

    @Override
    protected void shutDown() throws Exception {
        log.debug("ExampleService shutting down...");
    }
}
