package com.apica.UserMngService.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.apica.UserMngService.dtos.LoginUserDto;
import com.apica.UserMngService.model.LoginResponse;
import com.apica.UserMngService.model.User;
import com.apica.UserMngService.service.JwtService;
import com.apica.UserMngService.service.UsrMngrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UsrMngrController {
    private UsrMngrService usrMngrService; 
    private final JwtService jwtService;

    public UsrMngrController(@Qualifier("usrMngrServiceImpl") 
        UsrMngrService usrMngrService,
        JwtService jwtService) {
        this.usrMngrService = usrMngrService;
        this.jwtService = jwtService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        log.info("Inside getUserById");
        return ResponseEntity.ok(usrMngrService.getUser(id));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser() {
        log.info("Entering getCurrentUser");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication data:{}", authentication);
        Object var = authentication.getPrincipal(); 
        log.info("authentication.getPrincipal():{}", var);
        User currentUser = (User) var; 
        log.info("Exiting getCurrentUser");
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        log.info("Inside updateUser");
        return ResponseEntity.ok(usrMngrService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        log.info("Entering deleteUser");
        usrMngrService.deleteUser(id);
        log.info("Exiting deleteUser");
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Inside getAllUsers");
        return ResponseEntity.ok(usrMngrService.getAllUsers());
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User user) {
        log.info("Entering register");
        User registeredUser = usrMngrService.createUser(user);
        log.info("Exiting register");
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        log.info("Entering authenticate");
        User authenticatedUser = usrMngrService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        log.info("Exiting authenticate");
        return ResponseEntity.ok(loginResponse);
    }
    
    
}
