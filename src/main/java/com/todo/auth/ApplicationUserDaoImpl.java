package com.todo.auth;

import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static com.todo.security.ApplicationUserRole.*;
@Repository("fake")
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return getApplicationUsers().stream()
                .filter(user->
                        username.equals(user.getUsername())).findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> ApplicationUsers = Arrays.asList(
                new ApplicationUser(
                        passwordEncoder.encode("password1"),
                        "anna", STUDENT.getGrantedAuthorities(),
                        true, true, true, true),

                new ApplicationUser(
                        passwordEncoder.encode("password2"),
                        "linda", ADMIN.getGrantedAuthorities(),
                        true, true, true, true),

                new ApplicationUser(
                        passwordEncoder.encode("password3"),
                        "tom", ADMINTRAINEE.getGrantedAuthorities(),
                        true, true, true, true));
   return ApplicationUsers;
    }
}
