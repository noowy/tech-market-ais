package com.technolog.techmarketais.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ConfigurationProperties("com.technolog.techmarketais.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
}
