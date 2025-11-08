package com.todo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
//import com.google.common.collect.Sets;
import static com.todo.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    STUDENT(Set.of()),
    ADMIN(Set.of(COURSE_READ, COURSE_WRITE, STUDENT_WRITE, STUDENT_READ)),
    ADMINTRAINEE(Set.of(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole (Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
       Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(permission ->
                new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
       return permissions;
    }
}
