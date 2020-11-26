package org.interview.aequilibrium.service.impl;

import org.interview.aequilibrium.api.hateoas.TransformerResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.persistence.TransformerRepository;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
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
                .map(entity -> TransformerResource.createInstance(entity))
                .collect(Collectors.toList());
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
//        if (repository.findByName(transformer.getName()) != null) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
        repository.save(transformer);
        return Response.noContent().build();
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
            transformer.setSkill(transformerInput.getSkill());
            repository.save(transformer);
            return Response.noContent().build();
        }
    }

    @Transactional
    public Response deleteTransformer(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return Response.noContent().build();
        }
        return Response.noContent().build();
    }

    public List<Transformer> getTransformers(Integer... ids) {
        if (ids == null || ids.length == 0){
            return Collections.emptyList();
        }
        return repository.findAllById(Arrays.asList(ids));
    }
}
