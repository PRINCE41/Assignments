package com.apica.UserMngService.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apica.UserMngService.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
