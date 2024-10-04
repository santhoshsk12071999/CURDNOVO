package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.core.annotation.Order;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs/swagger-config").permitAll()
//            .antMatchers("/products/**").hasRole("ADMIN")
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic();
        
        
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs/swagger-config").authenticated() // Allow access to Swagger UI and API docs
//        .antMatchers("/products/**").hasAnyRole("ADMIN", "USER") 
        .antMatchers("/products/**").hasRole("USER") // Protect other endpoints
        .anyRequest().authenticated()
        .and()
        .httpBasic();
        
        
        
//        .antMatcher("/swagger-ui/**")
//        .authorizeRequests()
//        .anyRequest().authenticated() // Require authentication for Swagger UI
//        .and()
//        .httpBasic() // Use HTTP Basic authentication
//        .and()
//        .csrf().disable();
//        
        
        
//        .csrf().disable()
//        .authorizeRequests()
//        .anyRequest().permitAll();
    }
}
