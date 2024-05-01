package com.apica.UserMngService.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import lombok.Data;
import com.apica.UserMngService.util.RoleEnum;
import java.util.Date;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(nullable = false)
    private String description;

    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    
    @Column(name = "updated_at")
    private Date updatedAt;

}