package com.sundev.music.data_seeder;

import com.sundev.music.model.Permission;
import com.sundev.music.model.Role;
import com.sundev.music.model.Users;
import com.sundev.music.service.PermissionService;
import com.sundev.music.service.RoleService;
import com.sundev.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Seeder for permissions
        createPermissions();

        // Seeder for roles and assigning permissions to them
        createRoles();

        // Seeder for users and assigning roles to them
        createUsers();
    }

    private void createPermissions() {
        if (permissionService.getAllPermissions().isEmpty()) {
            Permission readPermission = new Permission(null, "READ");
            Permission writePermission = new Permission(null, "WRITE");
            Permission editPermission = new Permission(null, "EDIT");

            permissionService.saveOrUpdatePermission(readPermission);
            permissionService.saveOrUpdatePermission(writePermission);
            permissionService.saveOrUpdatePermission(editPermission);
        }
    }

    private void createRoles() {
        if (roleService.getAllRoles().isEmpty()) {
            // Creating a Role and Assigning Permissions
            Set<Permission> permissions = new HashSet<>();
            permissions.add(permissionService.getAllPermissions().get(0)); // READ permission
            permissions.add(permissionService.getAllPermissions().get(1)); // WRITE permission

            Role adminRole = new Role(null, "ADMIN", permissions);
            roleService.saveOrUpdateRole(adminRole);

            // Creating another role without WRITE permission
            Set<Permission> guestPermissions = new HashSet<>();
            guestPermissions.add(permissionService.getAllPermissions().get(0)); // READ permission

            Role guestRole = new Role(null, "GUEST", guestPermissions);
            roleService.saveOrUpdateRole(guestRole);
        }
    }

    private void createUsers() {
        if (userService.getUsers().isEmpty()) {
            // Create an Admin User
            Users adminUser = new Users();
            adminUser.setUsername("admin");
            adminUser.setPassword("password");  // Make sure to hash this password in production
            adminUser.setFullName("Admin User");
            adminUser.setEmail("admin@example.com");
            adminUser.setStatus(true);
            adminUser.setRoles(Set.of(roleService.getAllRoles().get(0)));  // Assigning Admin Role

            userService.saveUsers(adminUser);

            // Create a Guest User
            Users guestUser = new Users();
            guestUser.setUsername("guest");
            guestUser.setPassword("guest");
            guestUser.setFullName("Guest User");
            guestUser.setEmail("guest@example.com");
            guestUser.setStatus(true);
            guestUser.setRoles(Set.of(roleService.getAllRoles().get(1)));  // Assigning Guest Role

            userService.saveUsers(guestUser);
        }
    }
}
