package org.stepwiselabs.persnickety.dao;

import org.stepwiselabs.persnickety.annotation.Query;
import org.stepwiselabs.persnickety.annotation.Update;
import org.stepwiselabs.persnickety.model.VersionableEntity;
import org.stepwiselabs.persnickety.util.Page;

import java.util.Optional;

public interface VersionableEntityDAO<M extends VersionableEntity>  {

    @Query( constraint = "entity-id = :entityId AND version = :version")
    Optional<M> getSpecificEntityVersion(String entityId, Long version);

    @Query( constraint = "entity-id = :entityId AND version = currentVersion")
    Optional<M> getCurrentVersion(String entityId);

    @Query( constraint = "entity-id = :entityId", orderBy = "version DESC")
    Page<M> getAllVersions(String entityId);

    @Update( constraint = "entity-id = :issue.entityId AND version = :issue.version")
    Optional<M> update(M issue);
}
