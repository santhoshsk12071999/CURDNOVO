package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1) // Ensures this configuration has higher precedence
public class SwaggerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .antMatcher("/swagger-ui/**")
//            .authorizeRequests()
//            .anyRequest().permitAll() // Permit all requests to Swagger UI
//            .and()
//            .csrf().disable(); // Disable CSRF protection for Swagger UI
        
        .antMatcher("/swagger-ui/**")
        .authorizeRequests()
        .anyRequest().authenticated() // Require authentication for Swagger UI
        .and()
        .httpBasic() // Use HTTP Basic authentication
        .and()
        .csrf().disable();
        
        
    }
}

