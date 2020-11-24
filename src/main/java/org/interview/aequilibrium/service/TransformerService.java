package org.interview.aequilibrium.service;

import org.interview.aequilibrium.model.Transformer;

import javax.ws.rs.core.Response;

public interface TransformerService {
    public Response getTransformers();
    public Response insertTransformer(Transformer transformer);
    public Response updateTransformer(Transformer transformerInput);
    public Response deleteTransformer(Integer id);
}
