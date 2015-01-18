package com.example.app;

import com.google.common.util.concurrent.ServiceManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.voodoodyne.gwizard.config.ConfigModule;
import com.voodoodyne.gwizard.hibernate.HibernateModule;
import com.voodoodyne.gwizard.logging.LoggingModule;
import com.voodoodyne.gwizard.rest.RestModule;
import com.voodoodyne.gwizard.services.ServicesModule;

import java.io.File;

/**
 * Set up the injector and start the service manager.
 */
public class Main {
    public static int DEFAULT_SERVICE_STARTUP_TIMEOUT = 5;
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("First argument needs to be a yaml config file, doofus");
			return;
		}

		Injector injector = Guice.createInjector(
				new ExampleModule(),
				new ConfigModule(new File(args[0]), ExampleConfig.class),
				new LoggingModule(),
				new RestModule(),
				new HibernateModule(),
                                new ServicesModule());

                // start service manager (which will launch web service)
                injector.getInstance(ServiceManager.class)
                        .startAsync()
                        .awaitHealthy();

	}
}
