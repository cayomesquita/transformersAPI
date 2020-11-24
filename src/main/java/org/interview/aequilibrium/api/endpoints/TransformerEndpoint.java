package org.interview.aequilibrium.api.endpoints;

import org.interview.aequilibrium.api.hateoas.TransformerResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.persistence.TransformerRepository;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/transformers")
@Produces("application/json")
@Consumes("application/json")
public class TransformerEndpoint {

	@Autowired
	private TransformerService transformerService;

	@GET
	@Transactional
	public Response getTransformers() {
		return transformerService.getTransformers();
	}

	@POST
	public Response insertTransformer(Transformer transformer) {
		return transformerService.insertTransformer(transformer);
	}

	@PUT
	@Path("/{transformerId}")
	@Transactional
	public Response updateTransformer(Transformer transformerInput, @PathParam("transformerId") Integer id) {
		transformerInput.setId(id);
		return transformerService.updateTransformer(transformerInput);
	}

	@DELETE
	@Path("/{transformerId}")
	public Response deleteTransformer(@PathParam("transformerId") Integer id) {
		return transformerService.deleteTransformer(id);
	}

}
