package com.example.app;

import com.voodoodyne.gwizard.hibernate.DatabaseConfig;
import com.voodoodyne.gwizard.logging.LoggingConfig;
import com.voodoodyne.gwizard.web.WebConfig;
import lombok.Data;

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
	private LoggingConfig logging = new LoggingConfig();
	private WebConfig web = new WebConfig();
	private DatabaseConfig database = new DatabaseConfig();
}
