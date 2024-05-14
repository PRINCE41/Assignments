package com.apica.UserMngService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apica.UserMngService.model.User;
import com.apica.UserMngService.service.UsrMngrService;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UsrMngrService usrMngrService;

    public AdminController(UsrMngrService usrMngrService) {
        this.usrMngrService = usrMngrService;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody User user) {
        User createdAdmin = usrMngrService.createAdministrator(user);
        return ResponseEntity.ok(createdAdmin);
    }
}
