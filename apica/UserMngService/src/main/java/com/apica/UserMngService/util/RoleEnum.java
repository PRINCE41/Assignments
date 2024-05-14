package com.apica.UserMngService.util;

import lombok.Getter;

@Getter
public enum RoleEnum {
    USER(1, "USER"),
    ADMIN(2, "ADMIN"),
    SUPER_ADMIN(3, "SUPER_ADMIN");

    private final int id;
    private final String name;

    RoleEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
}
