package org.interview.aequilibrium.persistence;

import org.interview.aequilibrium.model.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Transformer repository.
 */
public interface TransformerRepository extends JpaRepository<Transformer, Integer> {

    /**
     * Find by name transformer.
     *
     * @param name the name
     * @return the transformer
     */
    Transformer findByName(String name);
}
