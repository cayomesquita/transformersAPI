package org.interview.aequilibrium.service;

import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * The interface Battle service.
 */
public interface BattleService {

    /**
     * Gets battle result.
     *
     * @param ids the ids
     * @return the battle result
     */
    Response getBattleResult(Set<Integer> ids);

}
