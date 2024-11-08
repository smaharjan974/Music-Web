package com.sundev.music.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private Users user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // // Collect permissions from user's roles and convert to GrantedAuthority
        // Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
        //         .flatMap(role -> role.getPermissions().stream()) // Get permissions for each role
        //         .map(Permission::getName) // Extract the permission name
        //         .map(SimpleGrantedAuthority::new) // Convert each permission name to a GrantedAuthority
        //         .collect(Collectors.toSet());
        // authorities.forEach(authority -> System.out.println("Authority: " + authority.getAuthority())); // Log the
        //                                                                                                     // authorities
        Set<GrantedAuthority> authorities = new HashSet<>();


        // Add role as authority, prefixed with "ROLE_" for Spring Security compatibility
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

            // Add permissions as authorities
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });

        authorities.forEach(authority -> 
            System.out.println("Granted Authority: " + authority.getAuthority())); // Log authorities for debugging

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Or customize based on user properties
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Or customize based on user properties
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Or customize based on user properties
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus(); // Enable based on user's status field
    }
}
