package com.seguridad.app_seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de Seguridad de la Aplicación
 * 
 * CONTROL DE ACCESO:
 * ┌─────────────────────┬───────┬──────┐
 * │ RUTA                │ ADMIN │ USER │
 * ├─────────────────────┼───────┼──────┤
 * │ /login              │  ✅   │  ✅  │
 * │ /panel              │  ✅   │  ✅  │
 * │ /perfil             │  ✅   │  ✅  │
 * │ /clientes/**        │  ✅   │  ❌  │
 * │ /servicios/**       │  ✅   │  ❌  │
 * │ /contrataciones/**  │  ✅   │  ❌  │
 * │ /pagos/**           │  ✅   │  ❌  │
 * │ /programas/**       │  ✅   │  ❌  │
 * │ /facturas/**        │  ✅   │  ✅  │
 * │ /h2-console/**      │  ✅   │  ❌  │
 * └─────────────────────┴───────┴──────┘
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // ========== RUTAS PÚBLICAS ==========
                .requestMatchers("/login", "/css/**", "/style.css", "/js/**").permitAll()
                
                // ========== RUTAS PARA USUARIOS AUTENTICADOS (ADMIN + USER) ==========
                .requestMatchers("/panel").authenticated()
                .requestMatchers("/perfil").authenticated()
                .requestMatchers("/facturas/**").authenticated()
                
                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/panel", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        // Deshabilitar CSRF y permitir H2-Console
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
