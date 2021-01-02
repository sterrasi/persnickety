package org.stepwiselabs.persnickety.example.model;


import org.stepwiselabs.persnickety.annotation.*;


/**
 * Many to many join table between users and issues
 */
public interface IssueWatcher {

    @Column("user-id")
    @NaturalKey
    User getUser();

    @Column("issue-id")
    @NaturalKey
    Issue getIssue();

    @Column("workflow-only")
    @NotNull()
    Boolean isWorkflowOnly();
}
