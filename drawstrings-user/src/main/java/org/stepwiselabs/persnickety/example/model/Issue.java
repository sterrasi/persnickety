package org.stepwiselabs.persnickety.example.model;


import org.stepwiselabs.persnickety.annotation.*;

import java.util.List;

public interface Issue {

    @Column("id")
    @AutoIncrement
    @PrimaryKey
    Long getId();

    @Column("entity-id")
    // sequence is internal
    @EntityId(prefix = "DE", sequence = "issue-enitity-id-sequence")
    @NotNull
    @NaturalKey
    String getEntityId();

    @Column("version")
    @NotNull
    @NaturalKey
    Long getVersion();

    @Column("created-by")
    User getUser();

    @Column("assignee")
    User getAssignee();

    @Column("metadata")
    @JSON
    IssueMetadata getIssueMetadata();

    @Join(target = "watchers.issue-id",
            constraint = "watchers.workflow-only=true",
            keyColumn = "user-id")
    @Lazy
    List<User> getWorkflowWatchers();

    @Join(target = "watchers.issue-id",
            keyColumn = "user-id")
    @Lazy
    List<User> getAllWatchers();
}