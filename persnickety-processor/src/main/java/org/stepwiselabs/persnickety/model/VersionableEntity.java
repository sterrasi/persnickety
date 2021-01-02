package org.stepwiselabs.persnickety.model;

public class VersionableEntity extends Entity {

    private String entityId;
    private Long version;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
