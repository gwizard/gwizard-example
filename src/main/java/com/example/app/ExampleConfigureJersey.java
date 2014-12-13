package com.example.app;

import com.voodoodyne.gwizard.web.ConfigureJersey;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * This callback is your chance to register any jersey resources or packages to scan.
 * You can abuse Jersey as much as you like.
 */
public class ExampleConfigureJersey implements ConfigureJersey {
	@Override
	public void configure(ResourceConfig config) {
		config.packages("com.example.app");
	}
}
