package com.example.app.services;

import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;

/**
 * application-specific ServiceManager.Listener to handle Service failures.
 * 
 * <p/>If any services fail, the application will be shutdown. ExampleModule
 * will have created a shutdown hook which will stop any other services, and
 * the web server.
 */
public class ServiceFailureHandler extends ServiceManager.Listener {
    @Override
    public void failure(Service service) {
        // if there's a service failure, exit with an error return code.
        System.exit(1);
    }
}
