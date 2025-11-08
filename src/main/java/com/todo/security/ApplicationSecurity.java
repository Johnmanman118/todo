package com.todo.security;

import com.todo.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // used for the method level "PreAuthorize()" annotation
public class ApplicationSecurity {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/login", "/css/**", "/js/**").permitAll()
                          .anyRequest().authenticated())
//                    .httpBasic(Customizer.withDefaults()); // alternative to .formLogin(Customizer.withDefaults());
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

   @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



    /* these below lines woould have been set up inside the SecurityFilterChain Bean,
     *  next to ".requestMatchers("/", "/index", "/login", "/css/**", "/js/**").permitAll()"
     *  and before " .anyRequest().authenticated())"
     */

    //Role based authentication
//                            .requestMatchers("/api/tasks/id/**").hasRole(STUDENT.name())
//                            .requestMatchers("/api/tasks/create").hasRole(ADMIN.name())
//                            .requestMatchers("/api/tasks").hasRole(ADMINTRAINEE.name())
    //Authorities / permission based authentications
//                            .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                            .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                            .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                            .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(ADMIN.name(), STUDENT.name(), ADMINTRAINEE.name())



    /* this is replaced by a custom User Details Service implimentation*/
    //    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("anna").password(passwordEncoder().encode("password1"))
//                .authorities(STUDENT.getGrantedAuthorities()).build();
////                .roles(STUDENT.name()).build();
//
//        UserDetails user2 = User.withUsername("linda").password(passwordEncoder().encode("password2"))
//                .authorities(ADMIN.getGrantedAuthorities()).build();
//        //                .roles(ADMIN.name(), ADMINTRAINEE.name()).build();
//        UserDetails user3 = User.withUsername("tom").password(passwordEncoder().encode("password3"))
//                .authorities(ADMINTRAINEE.getGrantedAuthorities()).build();
////                .roles(ADMINTRAINEE.name()).build();
//        return new InMemoryUserDetailsManager(user1, user2, user3);
//    }




