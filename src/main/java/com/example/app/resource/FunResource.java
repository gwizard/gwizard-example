package com.example.app.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.app.ExampleConfig;
import lombok.Data;
import lombok.Value;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

/**
 * This resource really isn't very fun.
 */
@Path("/fun")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FunResource {

	private final ExampleConfig cfg;
	private final HttpHeaders headers;

	@Inject
	public FunResource(final ExampleConfig cfg, final HttpHeaders headers) {
		this.cfg = cfg;
		this.headers = headers;
	}

	@Value
	public static class Stuff {
		String foo;
	}

	@GET
	public Stuff stuff() {
		return new Stuff(cfg.getFoo());
	}

	@Timed
	@GET
	@Path("/headers")
	public HttpHeaders headers() {
		return headers;
	}
}
