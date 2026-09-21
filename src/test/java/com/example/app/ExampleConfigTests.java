package com.example.app;

import org.gwizard.config.ConfigModule;
import org.gwizard.config.ConfigProvider;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.google.common.truth.Truth.assertThat;

class ExampleConfigTests {
	@Test
	void readsTheExampleConfiguration() {
		final ExampleConfig config = ConfigProvider.read(
				new ConfigModule<>(ExampleConfig.class, new File("test.yml")),
				new ExampleModule().objectMapper());

		assertThat(config.getFoo()).isEqualTo("bar");
		assertThat(config.getWeb().getPort()).isEqualTo(8081);
		assertThat(config.getDatabase().getUrl()).isEqualTo("jdbc:h2:mem:test");
		assertThat(config.getLogging().getXml()).contains("ConsoleAppender");
	}
}
