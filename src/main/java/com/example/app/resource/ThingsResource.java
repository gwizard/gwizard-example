package com.example.app.resource;

import com.example.app.entity.Thing;
import com.google.inject.persist.Transactional;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;
import static com.voodoodyne.gwizard.hibernate.EM.em;

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

	@GET
	@Transactional
	public List<Thing> list() {
		// Gavin, are you responsible for this abomination?
		final CriteriaQuery<Thing> query = em().getCriteriaBuilder().createQuery(Thing.class);
		query.select(query.from(Thing.class));
		return em().createQuery(query).getResultList();
	}

	@GET
	@Transactional
	@Path("{thingId}")
	public Thing get(@PathParam("thingId") UUID thingId) {
		return em().find(Thing.class, thingId);
	}
}
