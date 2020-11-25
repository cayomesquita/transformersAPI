package org.interview.aequilibrium.service.impl;

import org.interview.aequilibrium.api.hateoas.TransformerResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.persistence.TransformerRepository;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Transformer service implementation.
 */
@Service
public class TransformerServiceImpl implements TransformerService {

    private static final String MSG_TRANSFORMER_TYPE_INVALID = "Transformer type invalid";

    @Autowired
    private TransformerRepository repository;

    public Response getTransformers() {
        List<TransformerResource> result = repository.findAll().stream()
                .map(entity -> TransformerResource.createInstance(entity)).collect(Collectors.toList());
        if (result == null) {
            return Response.noContent().build();
        }
        return Response.ok(result).build();
    }

    @Transactional
    public Response insertTransformer(Transformer transformer) {
        if (transformer.getType() == null){
            return Response.status(Response.Status.BAD_REQUEST).entity(MSG_TRANSFORMER_TYPE_INVALID).build();
        }
        if (repository.findByName(transformer.getName()) != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.ok(repository.save(transformer).getId()).build();
    }

    @Transactional
    public Response updateTransformer(Transformer transformerInput) {
        if (transformerInput.getId() == null) {
            return insertTransformer(transformerInput);
        }
        Transformer transformer = repository.findById(transformerInput.getId()).orElse(null);
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

    @Transactional
    public Response deleteTransformer(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
