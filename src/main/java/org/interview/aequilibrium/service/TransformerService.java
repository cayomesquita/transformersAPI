package org.interview.aequilibrium.service;

import org.interview.aequilibrium.model.Transformer;

import javax.ws.rs.core.Response;

/**
 * The interface Transformer service.
 */
public interface TransformerService {
    /**
     * Gets all transformers.
     *
     * @return Http Response with transformers
     */
    public Response getTransformers();

    /**
     * Insert new transformer if does not exist with the same name.
     *
     * @param transformer the transformer
     * @return Http Response with the transformer
     */
    public Response insertTransformer(Transformer transformer);

    /**
     * Update transformer or insert if does not exist.
     *
     * @param transformerInput the transformer input
     * @return Http Response
     */
    public Response updateTransformer(Transformer transformerInput);

    /**
     * Delete transformer.
     *
     * @param id the id
     * @return Http Response
     */
    public Response deleteTransformer(Integer id);
}
