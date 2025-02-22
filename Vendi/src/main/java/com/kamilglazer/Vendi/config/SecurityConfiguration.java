package com.kamilglazer.Vendi.config;

import com.kamilglazer.Vendi.domain.USER_ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/coupon/**").hasAuthority(USER_ROLE.ADMIN.name())
                        .requestMatchers("/api/category/**").hasAuthority(USER_ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE,"/api/product/**").hasAuthority(USER_ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT,"/api/product/**").hasAuthority(USER_ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.POST,"/api/product/**").hasAuthority(USER_ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/api/product/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
