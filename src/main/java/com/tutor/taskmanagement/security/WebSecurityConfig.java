package com.tutor.taskmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/login", "/register")
                .permitAll()
                .anyRequest()
                .hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        /*http.sessionManagement()
                .maximumSessions(10)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);*/
    }

    /* @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/login", "/register", "/resources/**","/css/**","/js/**","/bootstrap/**","/jquery/**").permitAll()
                .anyRequest()
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }*/
}
