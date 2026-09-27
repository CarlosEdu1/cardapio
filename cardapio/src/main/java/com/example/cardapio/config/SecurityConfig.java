package com.example.cardapio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable() //desativar necessidade de token CSRF para API REST que é resp por assegurar que o sistema sofra ataques ciber
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //define como Sem Estado a app
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/public/**").permitAll() //libera todas as rotas publicas para este patch
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()//qualquer outra req, exige auth
                        )
                        .httpBasic(basic -> basic.disable())); //desativa o Login básico padrão se preferir JWT
        return http.build();
    }
}
