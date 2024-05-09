package com.example.app.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.app.entity.Thing;
import com.google.inject.persist.Transactional;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;
import static org.gwizard.hibernate.EM.em;

/**
 * At least this resource is named appropriately.
 */
@Path("/things")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThingsResource {
	private static int count = 0;

	@POST
	@Transactional
	public Thing create() {
		final Thing thing = new Thing("thing " + count++);
		em().persist(thing);
		return thing;
	}

	@Timed
	@GET
	@Transactional
	public List<Thing> list() {
		// Gavin, are you responsible for this abomination?
		final CriteriaQuery<Thing> query = em().getCriteriaBuilder().createQuery(Thing.class);
		query.select(query.from(Thing.class));
		return em().createQuery(query).getResultList();
	}

	@Timed
	@GET
	@Transactional
	@Path("{thingId}")
	public Thing get(@PathParam("thingId") UUID thingId) {
		return em().find(Thing.class, thingId);
	}
}
