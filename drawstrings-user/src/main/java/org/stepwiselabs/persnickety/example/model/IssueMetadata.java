package org.stepwiselabs.persnickety.example.model;


import java.time.ZonedDateTime;

/**
 * JSON serializable issue metadata
 */
public class IssueMetadata {

    private Integer priority;
    private ZonedDateTime lastUpdated;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public ZonedDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(ZonedDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
