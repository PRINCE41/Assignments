package com.apica.UserMngService.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.apica.UserMngService.dtos.LoginUserDto;
import com.apica.UserMngService.model.User;

@Service
public interface UsrMngrService {
    
    User getUser(String id);
    
    User createUser(User user);
    
    User updateUser(String id, User user);
    
    void deleteUser(String id);
    
    List<User> getAllUsers();

    public User authenticate(LoginUserDto input);
}