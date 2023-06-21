package com.project.schoolService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityCongiguration {
     private final UserDetailsService userDetailsService;

    public SecurityCongiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
/*

    @Override
     protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/login").permitAll() // Allow access to the login API
                    .antMatchers("/laptop-parts/**").hasAnyRole("SIMPLE_USER", "ADMIN")
                    .antMatchers("/tickets/**").hasAnyRole("SIMPLE_USER", "ADMIN")
                    .antMatchers("/students/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
     }

      @Bean
      public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    }
 */
    }

