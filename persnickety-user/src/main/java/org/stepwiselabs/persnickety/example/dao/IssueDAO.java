package org.stepwiselabs.persnickety.example.dao;

import org.stepwiselabs.persnickety.dao.VersionableEntityDAO;
import org.stepwiselabs.persnickety.util.Page;
import org.stepwiselabs.persnickety.annotation.DAO;
import org.stepwiselabs.persnickety.annotation.Query;
import org.stepwiselabs.persnickety.example.model.Issue;

@DAO(table = "issues", dto = Issue.class)
public interface IssueDAO extends VersionableEntityDAO {

    @Query(constraint = "state = 'open' AND purged = NULL")
    Page<Issue> getOpenIssues();
}
