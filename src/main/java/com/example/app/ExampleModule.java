package com.example.app;

import com.example.app.resource.FunResource;
import com.example.app.resource.ThingsResource;
import com.example.app.services.ExampleService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.jackson.Jackson;
import org.gwizard.healthchecks.HealthChecksConfig;
import org.gwizard.healthchecks.HealthChecksConfigProperties;
import org.gwizard.hibernate.DatabaseConfig;
import org.gwizard.logging.LoggingConfig;
import org.gwizard.web.WebConfig;
import jakarta.inject.Singleton;

/**
 * <p>Among the duties of your application module(s), you must explicitly bind every JAXRS resource class.
 * If you aren't sensitive to app start time, consider using Reflections to do this automatically.</p>
 *
 * <p>We must provide bindings for the LoggingConfig, WebConfig, and DatabaseConfig to use the
 * logging, rest, and hibernate modules.</p>
 */
public class ExampleModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(FunResource.class);
		bind(ThingsResource.class);

		bind(ExampleService.class).asEagerSingleton();
	}

	/** This objectmapper will get used for RESTEasy's JSON responses */
	@Provides
	@Singleton
	public ObjectMapper objectMapper() {
		return Jackson.newObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	@Provides
	public LoggingConfig loggingConfig(final ExampleConfig cfg) {
		return cfg.getLogging();
	}

	@Provides
	public WebConfig webConfig(final ExampleConfig cfg) {
		return cfg.getWeb();
	}

	@Provides
	public DatabaseConfig databaseConfig(final ExampleConfig cfg) {
		return cfg.getDatabase();
	}

	@Provides
	public HealthChecksConfig healthChecksConfig(final ExampleConfig cfg) {
		return cfg.getHealthChecks();
	}
}
