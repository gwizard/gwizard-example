package com.example.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.voodoodyne.gwizard.config.ConfigModule;
import com.voodoodyne.gwizard.logging.LoggingModule;
import com.voodoodyne.gwizard.web.WebModule;
import com.voodoodyne.gwizard.web.WebServer;
import java.io.File;

/**
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(
				new ConfigModule(new File(args[0]), ExampleConfig.class),
				new LoggingModule(),
				new WebModule("com.example.app"),
				//new HibernateModule(),
				new ExampleAppModule());

		injector.getInstance(WebServer.class).start();
	}
}
