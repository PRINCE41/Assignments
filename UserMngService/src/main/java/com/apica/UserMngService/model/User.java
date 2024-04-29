package com.apica.UserMngService.model;

import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;


@Data
@RedisHash("User")
public class User implements UserDetails{

    @Id
    @Column(unique = true, length = 20, nullable = false)
    private String uId;
    @Column(nullable = false)
    private String usrName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, length = 100, nullable = false)
    private String email;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return uId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}