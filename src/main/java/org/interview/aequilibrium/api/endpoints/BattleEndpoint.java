package org.interview.aequilibrium.api.endpoints;

import org.interview.aequilibrium.api.hateoas.BattleResultResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.service.BattleService;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Transformer endpoint.
 * Path /transformers
 *
 */
@Component
@Path("/battles")
@Produces("application/json")
@Consumes("application/json")
public class BattleEndpoint {

	@Autowired
	private BattleService battleService;

	/**
	 * Gets all transformers.
	 *
	 * @return the transformers
	 */
	@GET
	@Path("/result")
	public Response getBattleResult(@QueryParam("ids") List<Integer> ids) {
		Set<Integer> idCol = new HashSet<>(ids);
		return battleService.getBattleResult(idCol);
	}

}
