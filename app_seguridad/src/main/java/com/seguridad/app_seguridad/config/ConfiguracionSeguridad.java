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
 * CONTROL DE ACCESO POR ROL:
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
 * │ /instructores/**    │  ✅   │  ❌  │
 * │ /facturas/**        │  ✅   │  ✅  │
 * │ /h2-console/**      │  ✅   │  ❌  │
 * └─────────────────────┴───────┴──────┘
 */
@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public SecurityFilterChain filtroSeguridad(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // RUTAS PUBLICAS
                .requestMatchers(
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/img/**"
                ).permitAll()

                // RUTAS SOLO PARA ADMIN
                .requestMatchers(
                        "/clientes/**",
                        "/servicios/**",
                        "/contrataciones/**",
                        "/pagos/**",
                        "/programas/**",
                        "/instructores/**",
                        "/h2-console/**"
                ).hasRole("ADMIN")

                // RUTAS PARA ADMIN Y USER (Descargar facturas)
                .requestMatchers("/facturas/**").hasAnyRole("ADMIN", "USER")

                // RUTAS PARA ADMIN Y USER
                .requestMatchers(
                        "/panel",
                        "/perfil/**"
                ).hasAnyRole("ADMIN", "USER")

                // CUALQUIER OTRA REQUIERE LOGIN
                .anyRequest().authenticated()
            )

            // LOGIN
            .formLogin(login -> login
                    .loginPage("/login")
                    .defaultSuccessUrl("/panel", true)
                    .permitAll()
            )

            // LOGOUT
            .logout(logout -> logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            );

        // H2-CONSOLE
        http.csrf(csrf -> csrf.disable());
        http.headers(headers ->
                headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}