package com.example.app.test;

import com.example.app.ExampleModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.voodoodyne.gwizard.hibernate.HibernateModule;
import com.voodoodyne.gwizard.logging.LoggingModule;
import com.voodoodyne.gwizard.services.ServicesModule;
import lombok.extern.slf4j.Slf4j;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

/**
 * Some common behavior for all tests. Sets up an injector suitable for executing JAXRS resources
 * directly, exactly as the web container would.
 */
@Slf4j
public class TestBase {

	protected Injector injector;

	/** */
	@BeforeMethod
	public void initializeTestBase() {
		MockitoAnnotations.initMocks(this);

		injector = Guice.createInjector(
				Modules.override(
					new LoggingModule(),
					new HibernateModule(),
                                        new ServicesModule(),
					new ExampleModule(),
					new TestModule())
						.with(overrideModule()));
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
