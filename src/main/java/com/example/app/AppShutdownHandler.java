package com.example.app;

import com.google.common.util.concurrent.ServiceManager;
import com.google.inject.Provider;
import com.voodoodyne.gwizard.web.WebServer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * an eager singleton bound in ExampleModule. Registers a shutdown 
 * handler that will stop the web server and running services
 */
@Slf4j
public class AppShutdownHandler {
    public static final int DEFAULT_STOP_TIMEOUT = 5; 
    private final WebServer web;
    private final Provider<ServiceManager> serviceManager;
    
    @Inject
    public AppShutdownHandler(WebServer web, Provider<ServiceManager> serviceManager) {
        this.web = web;
        this.serviceManager = serviceManager;
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                handleShutdown();
            }
        });
    }
    
    public void handleShutdown() {
        try {
            log.trace("Shutting down web service...");
            web.stop();
        } catch (Exception ex) {
            log.error("Exception shutting down web service", ex);
        }
        
        try {
            log.trace("Shutting down services...");
            // Give the services no more than 5 seconds to stop 
            serviceManager.get().stopAsync().awaitStopped(DEFAULT_STOP_TIMEOUT, TimeUnit.SECONDS);
        } catch (TimeoutException ex) {
            log.error("Timeout waiting for service shutdown", ex);
        }
    }
    
}
