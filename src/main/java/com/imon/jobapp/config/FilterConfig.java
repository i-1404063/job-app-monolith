package com.imon.jobapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class FilterConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new CustomHeaderFilter(), BasicAuthenticationFilter.class)
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/companies/**").permitAll().anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
