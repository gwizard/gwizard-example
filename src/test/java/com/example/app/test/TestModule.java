package com.example.app.test;

import com.example.app.ExampleConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.specimpl.ResteasyHttpHeaders;
import javax.inject.Singleton;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;

/**
 */
@Slf4j
public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		// Managing servlet stuff in a test environment can sometimes be tricky, but basically just bind what
		// you need. If you end up needing request scope, you bind the scope yourself.
		bind(HttpHeaders.class).toInstance(new ResteasyHttpHeaders(new MultivaluedHashMap<String, String>()));
	}

	@Provides
	@Singleton
	public ExampleConfig exampleConfig() {
		ExampleConfig cfg = new ExampleConfig();
		cfg.getDatabase().setDriverClass("org.h2.Driver");
		cfg.getDatabase().setUser("sa");
		cfg.getDatabase().setUrl("jdbc:h2:mem:test");
		cfg.getDatabase().getProperties().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		cfg.getDatabase().getProperties().put("hibernate.hbm2ddl.auto", "create");

		return cfg;
	}
}
