package com.example.app;

import com.example.app.resource.FunResource;
import com.example.app.resource.ThingsResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.voodoodyne.gwizard.hibernate.DbConfig;
import com.voodoodyne.gwizard.web.WebConfig;
import javax.inject.Singleton;

/**
 * Among the duties of your application module(s), you must explicitly bind every JAXRS resource class.
 * Consider using Reflections to do this automatically.
 */
public class ExampleAppModule extends AbstractModule {
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
	public WebConfig webConfig(ExampleConfig cfg) {
		return cfg.getWeb();
	}

	@Provides
	public DbConfig dbConfig(ExampleConfig cfg) {
		return cfg.getDb();
	}
}
