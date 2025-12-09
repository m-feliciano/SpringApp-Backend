package com.feliciano.store.config.security;

import com.feliciano.store.security.JWTAuthorizationFilter;
import com.feliciano.store.security.JWTUtil;
import com.feliciano.store.security.UsernamePasswordAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {
    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**"};
    private static final String[] PUBLIC_MATCHERS_GET = {"/api/v1/products/**", "/api/v1/categories/**", "/api/v1/states/**"};
    private static final String[] PUBLIC_MATCHERS_POST = {"/api/v1/clients/**", "/api/v1/auth/forgot/**"};

    private final Environment environment;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JWTUtil jwtUtil;

    @Autowired
    public SecurityFilterChainConfig(Environment environment,
                                     JWTAuthorizationFilter authorizationFilter,
                                     AuthenticationProvider authenticationProvider,
                                     JWTUtil jwtUtil) {
        this.environment = environment;
        this.jwtUtil = jwtUtil;
        this.jwtAuthorizationFilter = authorizationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        }

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_MATCHERS).permitAll()
                        .requestMatchers(PUBLIC_MATCHERS_GET).permitAll()
                        .requestMatchers(PUBLIC_MATCHERS_POST).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilter(new UsernamePasswordAuthenticationTokenFilter(jwtUtil, authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))))
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
