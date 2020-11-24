package org.interview.aequilibrium.api.endpoints;

import org.interview.aequilibrium.api.hateoas.TransformerResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.persistence.TransformerRepository;
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

	public static final String MSG_TRANSFORMER_TYPE_INVALID = "Transformer type invalid";
	@Autowired
	private TransformerRepository repository;

	@GET
	@Transactional
	public Response getTransformers() {
		List<TransformerResource> result = repository.findAll().stream()
				.map(entity -> TransformerResource.getInstace(entity)).collect(Collectors.toList());
		if (result == null) {
			return Response.noContent().build();
		}
		return Response.ok(result).build();
	}

	@POST
	public Response insertTransformer(Transformer transformer) {
		if (transformer.getType() == null){
			return Response.status(Response.Status.BAD_REQUEST).entity(MSG_TRANSFORMER_TYPE_INVALID).build();
		}
		if (repository.findByName(transformer.getName()) != null) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.ok(repository.save(transformer).getId()).build();
	}

	@PUT
	@Path("/{transformerId}")
	@Transactional
	public Response updateTransformer(Transformer transformerInput, @PathParam("transformerId") Integer id) {
		Transformer transformer = repository.findOne(id);
		if (transformer == null) {
			return insertTransformer(transformerInput);
		} else {
			transformer.setCourage(transformerInput.getCourage());
			transformer.setEndurance(transformerInput.getEndurance());
			transformer.setFirepower(transformerInput.getFirepower());
			transformer.setIntelligence(transformerInput.getIntelligence());
			transformer.setName(transformerInput.getName());
			transformer.setRank(transformerInput.getRank());
			transformer.setSpeed(transformerInput.getSpeed());
			transformer.setStrength(transformerInput.getStrength());
			return Response.ok(repository.save(transformer).getId()).build();
		}
	}

	@DELETE
	@Path("/{transformerId}")
	public Response deleteTransformer(@PathParam("transformerId") Integer id) {
		if (repository.exists(id)) {
			repository.delete(id);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
