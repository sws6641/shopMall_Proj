package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private final AccessTokenAuthenticationFilter authenticationFilter;

    public WebSecurityConfig(
            AccessTokenAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.cors(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(
                authenticationFilter, BasicAuthenticationFilter.class);

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(antMatcher(HttpMethod.POST, "/session")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST, "/users")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/categories")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/products/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/backdoor/**")).permitAll()
                        .anyRequest().authenticated()
        );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
