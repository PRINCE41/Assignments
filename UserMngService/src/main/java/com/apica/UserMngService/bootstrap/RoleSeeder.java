package com.apica.UserMngService.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.apica.UserMngService.dao.RoleRepository;
import com.apica.UserMngService.model.Role;
import com.apica.UserMngService.util.RoleEnum;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        log.info("Entering loadRoles");
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
            RoleEnum.USER, "Default user role",
            RoleEnum.ADMIN, "Administrator role",
            RoleEnum.SUPER_ADMIN, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findById(roleName.getId());
            optionalRole.ifPresentOrElse(
            user -> {
                user.setUpdatedAt(new Date());
                log.info("Going to update RoleData:{} in repository", user);
                roleRepository.save(user);
            }, 
            () -> {
                Role roleToCreate = new Role();
                roleToCreate.setId(roleName.getId());
                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));
                roleToCreate.setCreatedAt(new Date());
                roleToCreate.setUpdatedAt(new Date());
                log.info("Going to create RoleData:{} in repository", roleToCreate);
                roleRepository.save(roleToCreate);
            });
        });
        log.info("Exiting loadRoles");
    }
}
