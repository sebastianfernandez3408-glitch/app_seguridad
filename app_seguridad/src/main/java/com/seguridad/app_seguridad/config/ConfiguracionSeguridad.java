package com.seguridad.app_seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public SecurityFilterChain filtro(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/h2-console/**"
                ).permitAll()
                .requestMatchers("/clientes/**").hasRole("ADMIN")
                .requestMatchers("/perfil/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/panel", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
            );

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}