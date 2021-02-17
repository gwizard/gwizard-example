package com.example.app.resource;

import com.example.app.entity.Thing;
import com.example.app.test.TestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * This tests the resource methods against a real in-memory database, no need to mock and stub the whole data
 * layer away. Mocking the data layer is a horrible practice, since for CRUD apps 90% of your application _is_
 * interaction with the data layer. If you're going to screw something up, it's likely to be all the hibernate
 * interaction.
 */
public class ThingsResourceTests extends TestBase {

	@Test
	public void thingsCanBeCreatedAndRetrieved() throws Exception {
		final Thing created = instance(ThingsResource.class).create();

		final Thing fetched = instance(ThingsResource.class).get(created.getId());

		assertThat(fetched.getName()).isEqualTo(created.getName());
	}

	@Test
	public void thingsCanBeListed() throws Exception {
		final Thing created = instance(ThingsResource.class).create();

		final List<Thing> fetched = instance(ThingsResource.class).list();

		assertThat(fetched).hasSize(1);
		assertThat(fetched.get(0).getName()).isEqualTo(created.getName());
	}
}
