package com.apica.UserMngService.bootstrap;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.apica.UserMngService.dao.RoleRepository;
import com.apica.UserMngService.dao.UserRepository;
import com.apica.UserMngService.model.Role;
import com.apica.UserMngService.model.User;
import com.apica.UserMngService.util.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@Order(2)
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(
        RoleRepository roleRepository,
        UserRepository  userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        User user = new User();
        user.setUserName("super-admin");
        user.setEmail("super.admin@email.com");
        user.setAddress("128, Officer Colony, LMP");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        

        Optional<Role> optionalRole = roleRepository.findById(RoleEnum.SUPER_ADMIN.getId());
        Optional<User> optionalUser = userRepository.findById(user.getUsername());

        if (optionalRole.isEmpty()) {
            log.warn("Unable to fetch SUPER_ADMIN Role data");
            return;
        }

        if(optionalUser.isPresent()){
            optionalUser.get().setUpdatedAt(new Date());
            log.info("Updating super-admin session data");
            userRepository.save(optionalUser.get());
            return;
        }
        user.setRole(optionalRole.get());
        user.setPassword(passwordEncoder.encode("123456"));
        log.info("Creating super-admin session data");
        userRepository.save(user);
    }
}