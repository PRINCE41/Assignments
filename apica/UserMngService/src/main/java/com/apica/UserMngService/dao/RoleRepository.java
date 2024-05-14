package com.apica.UserMngService.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.apica.UserMngService.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}