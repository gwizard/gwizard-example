package com.example.app.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.app.ExampleConfig;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

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
	public FunResource(ExampleConfig cfg, HttpHeaders headers) {
		this.cfg = cfg;
		this.headers = headers;
	}

	@Data
	public static class Stuff {
		private final String foo;
	}

	@ApiOperation(tags={"stuff", "configuration"}, value="Returns some of this application configuration e.g. foo")
	@ApiResponses(value={
			@ApiResponse(code=200, message="OK")
	})
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
