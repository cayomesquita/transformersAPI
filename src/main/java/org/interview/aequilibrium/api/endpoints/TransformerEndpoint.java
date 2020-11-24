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
	public Response insertTransformer(Transformer Transformer) {
		if (repository.findByName(Transformer.getName()) != null) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.ok(repository.save(Transformer).getId()).build();
	}

	@PUT
	@Path("/{transformerId}")
	@Transactional
	public Response updateTransformer(Transformer transformerInput, @PathParam("transformerId") Integer id) {
		Transformer Transformer = repository.findOne(id);
		if (Transformer == null) {
			return insertTransformer(transformerInput);
		} else {
			Transformer.setCourage(transformerInput.getCourage());
			Transformer.setEndurance(transformerInput.getEndurance());
			Transformer.setFirepower(transformerInput.getFirepower());
			Transformer.setIntelligence(transformerInput.getIntelligence());
			Transformer.setName(transformerInput.getName());
			Transformer.setRank(transformerInput.getRank());
			Transformer.setSpeed(transformerInput.getSpeed());
			Transformer.setStrength(transformerInput.getStrength());
			repository.save(Transformer);
		}
		return Response.ok().build();
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
