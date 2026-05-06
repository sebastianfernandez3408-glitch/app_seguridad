package com.seguridad.app_seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// DESACTIVADO: Usa SecurityConfig.java en su lugar
//@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public SecurityFilterChain filtro(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/h2-console/**", "/acceso-denegado").permitAll()
                .requestMatchers("/clientes/**").hasRole("ADMIN")
                .requestMatchers("/servicios/**").hasRole("ADMIN")
                .requestMatchers("/pagos/**").hasRole("ADMIN")
                .requestMatchers("/programas/**").hasRole("ADMIN")
                .requestMatchers("/contrataciones/**").hasRole("ADMIN")
                .requestMatchers("/facturas/**").hasRole("ADMIN")
                .requestMatchers("/perfil/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/panel", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
            )
            .exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/acceso-denegado")
            );

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}