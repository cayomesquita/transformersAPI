package org.interview.aequilibrium.service;

import org.springframework.http.ResponseEntity;

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
    ResponseEntity getBattleResult(Set<Integer> ids);

}
