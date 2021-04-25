package org.stepwiselabs.persnickety.example.dao;

import org.stepwiselabs.persnickety.annotation.DAO;
import org.stepwiselabs.persnickety.annotation.Query;
import org.stepwiselabs.persnickety.annotation.Update;
import org.stepwiselabs.persnickety.dao.EntityDAO;
import org.stepwiselabs.persnickety.example.model.User;
import org.stepwiselabs.persnickety.util.Page;

import java.util.Optional;

@DAO(table = "users", dto = User.class)
public interface UserDAO extends EntityDAO {

    @Query(constraint = "state = 'open' AND purged = NULL")
    Page<User> getOpenIssues();

    @Update(constraint = "userName = :user.userName")
    Optional<User> update(User user);
}
