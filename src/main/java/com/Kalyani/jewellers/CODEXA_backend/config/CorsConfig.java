package com.Kalyani.jewellers.CODEXA_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow requests from frontend (local)
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:8080",
                "http://127.0.0.1:8080"
        ));

        // Allow all common HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Allow all headers
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Expose headers to frontend
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));

        // Allow credentials (cookies, auth headers)
        configuration.setAllowCredentials(true);

        // Preflight cache duration
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
