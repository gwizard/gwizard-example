package com.example.app;

import com.example.app.services.ExampleService;
import com.example.app.services.ServiceFailureHandler;
import com.example.app.resource.FunResource;
import com.example.app.resource.ThingsResource;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.voodoodyne.gwizard.hibernate.DatabaseConfig;
import com.voodoodyne.gwizard.logging.LoggingConfig;
import com.voodoodyne.gwizard.web.WebConfig;
import javax.inject.Singleton;

/**
 * <p>Among the duties of your application module(s), you must explicitly bind every JAXRS resource class.
 * Consider using Reflections to do this automatically.</p>
 *
 * <p>We must provide bindings for the LoggingConfig, WebConfig, and DatabaseConfig to use the
 * logging, rest, and hibernate modules.</p>
 */
public class ExampleModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(FunResource.class);
		bind(ThingsResource.class);
                
                bind(AppShutdownHandler.class).asEagerSingleton();
                Multibinder.newSetBinder(binder(), ServiceManager.Listener.class)
                        .addBinding().to(ServiceFailureHandler.class);
                
                Multibinder.newSetBinder(binder(), Service.class)
                        .addBinding().to(ExampleService.class);
	}

	/** This objectmapper will get used for RESTEasy's JSON responses */
	@Provides
	@Singleton
	public ObjectMapper objectMapper() {
		return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
