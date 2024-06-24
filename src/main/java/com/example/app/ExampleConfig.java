package com.example.app;

import lombok.Data;
import org.gwizard.healthchecks.HealthChecksConfig;
import org.gwizard.healthchecks.HealthChecksConfigProperties;
import org.gwizard.hibernate.DatabaseConfig;
import org.gwizard.hibernate.DatabaseConfigProperties;
import org.gwizard.logging.LoggingConfig;
import org.gwizard.logging.LoggingConfigProperties;
import org.gwizard.web.WebConfig;
import org.gwizard.web.WebConfigProperties;

/**
 * Your configuration class can be any POJO, and there's nothing special about
 * the property names - although we recommend sticking to these standards.
 * Your Guice module will need to @Provide the relevant config classes for
 * logging, web, database, etc, and this is where they should come from.
 */
@Data
public class ExampleConfig {

	/** A bit of configuration for your own app */
	private String foo;

	/** Some standard bits of configuration */
	private LoggingConfigProperties logging = new LoggingConfigProperties();
	private WebConfigProperties web = new WebConfigProperties();
	private DatabaseConfigProperties database = new DatabaseConfigProperties();
	private HealthChecksConfigProperties healthChecks = new HealthChecksConfigProperties();
}
