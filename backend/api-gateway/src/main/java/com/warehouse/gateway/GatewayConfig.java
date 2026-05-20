package com.warehouse.gateway;

import org.springframework.context.annotation
    .Bean;
import org.springframework.context.annotation
    .Configuration;
import org.springframework.security.config
    .annotation.web.reactive
    .EnableWebFluxSecurity;
import org.springframework.security.config
    .web.server.ServerHttpSecurity;
import org.springframework.security.config
    .web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config
    .web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.config
    .web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.web.server
    .SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewayConfig {

    @Bean
    public SecurityWebFilterChain
            springSecurityFilterChain(
                ServerHttpSecurity http) {
        http
            .csrf(CsrfSpec::disable)
            .httpBasic(HttpBasicSpec::disable)
            .formLogin(FormLoginSpec::disable)
            .authorizeExchange(auth ->
                auth.anyExchange()
                    .permitAll());
        return http.build();
    }
}