package org.interview.aequilibrium.service;

import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * The interface Transformer service.
 */
public interface BattleService {

    /**
     * Gets all transformers.
     *
     * @return Http Response with transformers
     */
    Response getBattleResult(Set<Integer> ids);

}
