package com.example.app;

import com.google.inject.persist.Transactional;
import lombok.Data;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 */
@Path("/fun")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

	private final ExampleConfig cfg;

	@Inject
	public ExampleResource(ExampleConfig cfg) {
		this.cfg = cfg;
	}

	@Data
	public static class Stuff {
		private final String foo;
	}

	@GET
	public Stuff stuff() {
		return new Stuff(cfg.getFoo());
	}

	@GET
	@Path("txn")
	@Transactional
	public Stuff txn() {
		EM.em().clear();
		return new Stuff(cfg.getFoo());
	}
}
