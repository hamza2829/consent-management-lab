package com.hamza.consent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@SuppressWarnings("deprecation")
public class SecurityConfig {
    @Bean SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(a -> a.requestMatchers("/actuator/health", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).build();
    }
    @Bean UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("customer").password("change-me").roles("CUSTOMER").build(),
                User.withUsername("agent").password("change-me").roles("AGENT").build(),
                User.withUsername("auditor").password("change-me").roles("AUDITOR").build());
    }
    @Bean PasswordEncoder passwordEncoder() { return NoOpPasswordEncoder.getInstance(); }
}
