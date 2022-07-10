package com.dh.beTFI.dentalPractices.securityConfig;

import com.dh.beTFI.dentalPractices.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/patients/**",
                        "/dentists/**",
                        "/templates/patients/**",
                        "/templates/dentists/**")
                        .hasRole("ADMIN")
                .antMatchers(
                        "/appointments/**",
                        "/templates/appointments/**")
                        .hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated().and()
                .formLogin().and()
                .logout()
                .and().httpBasic(); // para que funcionen las requests en postman
    }
}