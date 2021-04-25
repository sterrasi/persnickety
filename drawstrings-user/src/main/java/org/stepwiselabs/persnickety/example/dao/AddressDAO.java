package org.stepwiselabs.persnickety.example.dao;

import org.stepwiselabs.persnickety.annotation.DAO;
import org.stepwiselabs.persnickety.annotation.Query;
import org.stepwiselabs.persnickety.annotation.Update;
import org.stepwiselabs.persnickety.dao.EntityDAO;
import org.stepwiselabs.persnickety.example.model.Address;
import org.stepwiselabs.persnickety.example.model.Issue;
import org.stepwiselabs.persnickety.util.Page;

import java.util.Optional;

@DAO(table = "addresses", dto = Address.class)
public interface AddressDAO {

    @Query()
    Page<Address> GetAll();




}
