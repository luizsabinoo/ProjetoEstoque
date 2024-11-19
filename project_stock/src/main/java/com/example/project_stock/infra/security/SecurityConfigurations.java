package com.example.project_stock.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity

public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/produtos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/produtos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/produtos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/produtos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/produtos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/localizacao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/localizacao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/localizacao/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/localizacao/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/localizacao/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/stock/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/stock").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/stock").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/stock/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/stock/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/fornecedor").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/fornecedores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/fornecedores/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/fornecedores/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/fornecedores/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();



    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
