package com.example.app.services;

import com.google.common.util.concurrent.AbstractIdleService;
import lombok.extern.slf4j.Slf4j;
import org.gwizard.services.Services;
import javax.inject.Inject;

/**
 * Example of a random service of your own devising. Guava services are started in parallel,
 * shortening your boot time. You should bind your service as an eager singleton, and in its
 * constructor, add yourself to the injected Services object.
 */
@Slf4j
public class ExampleService extends AbstractIdleService {

    @Inject
    public ExampleService(Services services) {
        services.add(this);
    }

    @Override
    protected void startUp() throws Exception {
        log.debug("ExampleService starting...");
    }

    @Override
    protected void shutDown() throws Exception {
        log.debug("ExampleService shutting down...");
    }
}
