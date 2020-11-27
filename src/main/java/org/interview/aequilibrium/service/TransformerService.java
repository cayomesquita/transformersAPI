package org.interview.aequilibrium.service;

import org.interview.aequilibrium.model.Transformer;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The interface Transformer service.
 */
public interface TransformerService {
    /**
     * Gets all transformers.
     *
     * @return Http Response with transformers
     */
    ResponseEntity getTransformers();

    /**
     * Insert new transformer if does not exist with the same name.
     *
     * @param transformer the transformer
     * @return Http Response with the transformer
     */
    ResponseEntity insertTransformer(Transformer transformer);

    /**
     * Update transformer or insert if does not exist.
     *
     * @param transformerInput the transformer input
     * @return Http Response
     */
    ResponseEntity updateTransformer(Transformer transformerInput);

    /**
     * Delete transformer.
     *
     * @param id the id
     * @return Http Response
     */
    ResponseEntity deleteTransformer(Integer id);

    /**
     * Get transformers by ids
     * @param ids
     * @return
     */
    List<Transformer> getTransformers(Integer... ids);
}
