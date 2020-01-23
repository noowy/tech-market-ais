package com.technolog.techmarketais.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@ConfigurationProperties("com.technolog.techmarketais.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private UserDetailsService userService;

    @Getter
    @Setter
    private String encoderSecret;

    @Autowired
    public SecurityConfig(UserDetailsService userService)
    {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder encoder()
    {
        return new StandardPasswordEncoder(encoderSecret);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
            .userDetailsService(userService)
            .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api").permitAll()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers("/home")
                    .hasRole("USER")
            .and()
                .formLogin()
                    .loginPage("/")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/?error=true")
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                .csrf().disable();
    }
}
