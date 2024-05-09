package com.example.app;

import com.example.app.entity.Thing;
import com.example.app.test.TestBase;
import com.google.inject.Module;
import org.gwizard.rest.RestModule;
import org.gwizard.services.Run;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;

/**
 * <p>This test starts the full web stack and issues real http requests against the target. Compare this against
 * the ThingsResourceTests which provide a more direct test of resource classes. Starting the full HTTP stack
 * does give you a more "thorough" test, but at the expense of more work and slower tests.</p>
 *
 * <p>IMNSHO, directly testing resources provides a "sweet spot" that lets you build a large amount of coverage
 * very quickly. However, you may wish to throw in a few web stack tests anyways.</p>
 */
public class FullWebStackTests extends TestBase {

	/** We need to get the RestModule included if we want the web stack to run */
	@Override
	protected Module overrideModule() {
		return new RestModule();
	}

	@BeforeEach
	public void setUpWebStack() throws Exception {
		injector.getInstance(Run.class).start();
	}

	@AfterEach
	public void tearDownWebStack() throws Exception {
		injector.getInstance(Run.class).stop();
	}

	/**
	 * This is for the RESTeasy client framework. If you want to do a lot of this this kind of coding/testing,
	 * it's a good idea to put the interface in the main project and make the resource classes impl the interface.
	 */
	@Path("/things")
	public interface ThingsClient {
		@POST
		Thing create();

		@GET
		List<Thing> list();

		@GET
		@Path("{thingId}")
		Thing get(@PathParam("thingId") UUID thingId);
	}

	private ThingsClient client() {
		ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
		ResteasyWebTarget target = client.target("http://localhost:8080/");

		return target.proxy(ThingsClient.class);
	}

	@Test
	public void thingsCanBeCreatedAndRetrieved() throws Exception {
		ThingsClient things = client();

		Thing created = things.create();

		Thing fetched = things.get(created.getId());

		assertThat(fetched.getName()).isEqualTo(created.getName());
	}

	@Test
	public void thingsCanBeListed() throws Exception {
		ThingsClient things = client();

		Thing created = things.create();

		List<Thing> fetched = things.list();

		assertThat(fetched).hasSize(1);
		assertThat(fetched.get(0).getName()).isEqualTo(created.getName());
	}
}
