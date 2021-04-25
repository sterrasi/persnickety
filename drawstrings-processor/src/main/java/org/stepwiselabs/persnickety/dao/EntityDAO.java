package org.stepwiselabs.persnickety.dao;

import org.stepwiselabs.persnickety.annotation.Query;
import org.stepwiselabs.persnickety.model.Entity;

import java.util.Optional;

public interface EntityDAO<M extends Entity> {

    @Query(constraint = "id = :id")
    Optional<Entity> getById(String id);

}
