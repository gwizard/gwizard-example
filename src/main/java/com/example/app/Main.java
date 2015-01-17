package com.example.app;

import com.google.common.util.concurrent.ServiceManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.voodoodyne.gwizard.config.ConfigModule;
import com.voodoodyne.gwizard.hibernate.HibernateModule;
import com.voodoodyne.gwizard.logging.LoggingModule;
import com.voodoodyne.gwizard.rest.RestModule;
import com.voodoodyne.gwizard.services.ServicesModule;
import com.voodoodyne.gwizard.web.WebServer;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Set up the injector and start the web server. Easy.
 */
public class Main {
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
                
                injector.getInstance(ServiceManager.class).startAsync();//.awaitHealthy(5, TimeUnit.SECONDS);

		injector.getInstance(WebServer.class).startJoin();
	}
}
