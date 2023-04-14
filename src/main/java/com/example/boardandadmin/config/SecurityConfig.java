package com.example.boardandadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
            )throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf().disable()   //jsp에서 post시 csrf가 났기때문에 일단 비활성화..
                .formLogin(withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));

        return http.build();
    }

}
