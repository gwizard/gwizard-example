package com.example.app;

import com.example.app.resource.FunResource;
import com.example.app.resource.ThingsResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.voodoodyne.gwizard.hibernate.DatabaseConfig;
import com.voodoodyne.gwizard.logging.LoggingConfig;
import com.voodoodyne.gwizard.web.WebConfig;
import javax.inject.Singleton;

/**
 * Among the duties of your application module(s), you must explicitly bind every JAXRS resource class.
 * Consider using Reflections to do this automatically.
 */
public class ExampleModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(FunResource.class);
		bind(ThingsResource.class);
	}

	@Provides
	@Singleton
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

	@Provides
	public LoggingConfig loggingConfig(ExampleConfig cfg) {
		return cfg.getLogging();
	}
	@Provides
	public WebConfig webConfig(ExampleConfig cfg) {
		return cfg.getWeb();
	}

	@Provides
	public DatabaseConfig databaseConfig(ExampleConfig cfg) {
		return cfg.getDatabase();
	}
}
