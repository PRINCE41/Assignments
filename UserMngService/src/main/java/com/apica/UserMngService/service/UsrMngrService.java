package com.apica.UserMngService.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.apica.UserMngService.dtos.LoginUserDto;
import com.apica.UserMngService.model.User;

@Service
public interface UsrMngrService {
    
    public User getUser(String id);
    
    public User createUser(User user);
    
    public User updateUser(String id, User user);
    
    public void deleteUser(String id);
    
    public List<User> getAllUsers();

    public User authenticate(LoginUserDto input);
}