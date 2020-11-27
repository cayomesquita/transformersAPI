package org.interview.aequilibrium.service.impl;

import org.interview.aequilibrium.api.hateoas.TransformerResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.persistence.TransformerRepository;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ResponseEntity getTransformers() {
        List<TransformerResource> result = repository.findAll().stream()
                .map(entity -> TransformerResource.createInstance(entity))
                .collect(Collectors.toList());
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @Transactional
    public ResponseEntity insertTransformer( Transformer transformer) {
        if (transformer.getType() == null){
            return ResponseEntity.badRequest().body(MSG_TRANSFORMER_TYPE_INVALID);
        }
//        if (repository.findByName(transformer.getName()) != null) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
        Integer newid = repository.save(transformer).getId();
        return ResponseEntity.ok(newid);
    }

    @Transactional
    public ResponseEntity updateTransformer(Transformer transformerInput) {
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
            return ResponseEntity.ok(transformer.getId());
        }
    }

    @Transactional
    public ResponseEntity deleteTransformer(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    public List<Transformer> getTransformers(Integer... ids) {
        if (ids == null || ids.length == 0){
            return Collections.emptyList();
        }
        return repository.findAllById(Arrays.asList(ids));
    }
}
