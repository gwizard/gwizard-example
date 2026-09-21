package com.example.app.test;

import com.example.app.ExampleModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.util.Modules;
import lombok.extern.slf4j.Slf4j;
import org.gwizard.hibernate.HibernateModule;
import org.gwizard.logging.LoggingModule;
import org.gwizard.metrics.MetricsModule;
import org.gwizard.services.Run;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Some common behavior for all tests. Sets up an injector suitable for executing JAXRS resources
 * directly, exactly as the web container would.
 */
@Slf4j
public class TestBase {

	protected Injector injector;

	/** */
	@BeforeEach
	void initializeTestBase() {
		injector = Guice.createInjector(
				Modules.override(
					new LoggingModule(),
					new HibernateModule(),
					new MetricsModule(),
					new ExampleModule(),
					new TestModule())
						.with(overrideModule()));

		injector.injectMembers(this);
		instance(Run.class).start();
	}

	@AfterEach
	void shutDownTestBase() {
		try {
			instance(Run.class).stop();
		} finally {
			instance(PersistService.class).stop();
		}
	}

	/** Override this method in a test class if you want special guice behavior */
	protected Module overrideModule() {
		return new EmptyModule();
	}

	/** Convenience method */
	protected <T> T instance(Class<T> clazz) {
		return injector.getInstance(clazz);
	}
}
