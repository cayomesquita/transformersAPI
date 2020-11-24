package org.interview.aequilibrium.persistence;

import org.interview.aequilibrium.model.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformerRepository extends JpaRepository<Transformer, Integer> {

    Transformer findByName(String name);
}
