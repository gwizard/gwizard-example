package com.example.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.voodoodyne.gwizard.hibernate.DbConfig;
import com.voodoodyne.gwizard.web.ConfigureJersey;
import com.voodoodyne.gwizard.web.WebConfig;
import javax.inject.Singleton;

/**
 */
public class ExampleAppModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ConfigureJersey.class).to(ExampleConfigureJersey.class);

		//requestStaticInjection(EM.class);
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
