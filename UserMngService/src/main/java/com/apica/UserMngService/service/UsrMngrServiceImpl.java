package com.apica.UserMngService.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.apica.UserMngService.dao.RoleRepository;
import com.apica.UserMngService.dao.UserRepository;
import com.apica.UserMngService.dtos.LoginUserDto;
import com.apica.UserMngService.model.Role;
import com.apica.UserMngService.model.User;
import com.apica.UserMngService.util.CommonConsts;
import com.apica.UserMngService.util.RoleEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("usrMngrServiceImpl")
public class UsrMngrServiceImpl implements UsrMngrService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final KafkaPublisher kafkaPublisher;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UsrMngrServiceImpl(
        UserRepository userRepository,
        KafkaPublisher kafkaPublisher,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.kafkaPublisher = kafkaPublisher;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    
    @Override
    public User getUser(String id) {
        log.info("Id:{} Entering getUser", id);
        User user = null;
        if (id != null) {
            kafkaPublisher.publishJournalEntry(id, CommonConsts.API_GET_REQUEST_ACTION);
            user = userRepository.findById(id).orElse(null);
        }
        log.info("Id:{} Exiting getUser", id);
        return user;
    }
    
    @Override
    public User createUser(User user) {
        log.info("Entering createUser with data:{}", user);
        Optional<Role> optionalRole = roleRepository.findById(RoleEnum.USER.getId());
        if (optionalRole.isEmpty()) {
            log.warn("Unknown user-role found:{}", user);
            return null;
        }
        if(user != null){
            if (user.getUId() == null) {
                String id = UUID.randomUUID().toString();
                user.setUId(id);
                kafkaPublisher.publishJournalEntry(id, CommonConsts.REGISTRATION_ACTION);
            }
            else {
                Optional<User> optionalUser = userRepository.findById(user.getUId());
                if (optionalUser.isPresent()) {
                    log.warn("Id:{} User already exist", user.getUId());
                    return null;
                }
                kafkaPublisher.publishJournalEntry(user.getUId(), CommonConsts.REGISTRATION_ACTION);
            }
            user.setRole(optionalRole.get());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            log.info("Id:{} Exiting createUser", user.getUId());
            return userRepository.save(user);
        }
        log.warn("Invalid data:{} provided in request. Exiting createUser", user);
        return null;
        
    }
    
    @Override
    public User updateUser(String id, User user) {
        log.info("Id:{} Entering updateUser", id);
        if(user != null){
            Optional<User> optionalUser = userRepository.findById(id);
            if (!optionalUser.isPresent()) {
                log.warn("Id:{} No user found to update", id);
                return null;
            } else {
                User existingUser = optionalUser.get();
                user.setUpdatedAt(new Date());
                if (existingUser.getCreatedAt() == null) {
                    user.setCreatedAt(new Date());
                } else {
                    user.setCreatedAt(existingUser.getCreatedAt());
                }
                kafkaPublisher.publishJournalEntry(id, CommonConsts.PROFILE_UPDATE_ACTION);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            log.info("Id:{} Exiting updateUser", id);
            return userRepository.save(user);
        }
        log.warn("Invalid data:{} provided in request. Exiting updateUser", user);
        return null;
    }
    
    @Override
    public void deleteUser(String id) {
        log.info("Id:{} Entering deleteUser", id);
        if (id != null) {
            kafkaPublisher.publishJournalEntry(id, CommonConsts.USER_DELETE_ACTION);
            userRepository.deleteById(id);
            log.info("Id:{} Exiting deleteUser", id);
        }
        else {
            log.warn("Invalid Id:{} provided in request. Exiting deleteUser", id);
        }
    }
    
    @Override
    public List<User> getAllUsers() {
        log.info("Entering getAllUsers");
        // Random ID assigned for Audit purpose
        kafkaPublisher.publishJournalEntry(Uuid.randomUuid().toString(), CommonConsts.API_GET_ALL_REQUEST_ACTION);
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        log.info("Exiting getAllUsers");
        return users;
    }


    @Override
    public User createAdministrator(User input) {
        Optional<Role> optionalRole = roleRepository.findById(RoleEnum.ADMIN.getId());
        if (optionalRole.isEmpty()) {
            return null;
        }
        var user = new User();
        user.setUsrName(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());
        user.setAddress(input.getAddress());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginUserDto input) {
        log.info("Inside authenticate with LoginUserDto:{}", input);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUId(),
                        input.getPassword()
                )
        );

        return userRepository.findById(input.getUId())
                .orElseThrow();
    }

}