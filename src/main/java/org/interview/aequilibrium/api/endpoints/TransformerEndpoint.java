package org.interview.aequilibrium.api.endpoints;

import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Transformer endpoint.
 * Path /transformers
 *
 */
@Component
@Path("/transformers")
@Produces("application/json")
@Consumes("application/json")
public class TransformerEndpoint {

	@Autowired
	private TransformerService transformerService;

	/**
	 * Gets all transformers.
	 *
	 * @return the transformers
	 */
	@GET
	public Response getTransformers() {
		return transformerService.getTransformers();
	}

	/**
	 * Insert transformer.
	 *
	 * @param transformer the transformer
	 * @return the response
	 */
	@POST
	public Response insertTransformer(Transformer transformer) {
		return transformerService.insertTransformer(transformer);
	}

	/**
	 * Update transformer.
	 *
	 * @param transformerInput the transformer
	 * @param id               the transformer id
	 * @return the response
	 */
	@PUT
	@Path("/{transformerId}")
	public Response updateTransformer(Transformer transformerInput, @PathParam("transformerId") Integer id) {
		transformerInput.setId(id);
		return transformerService.updateTransformer(transformerInput);
	}

	/**
	 * Delete transformer.
	 *
	 * @param id the transformer id
	 * @return the response
	 */
	@DELETE
	@Path("/{transformerId}")
	public Response deleteTransformer(@PathParam("transformerId") Integer id) {
		return transformerService.deleteTransformer(id);
	}

}
