package me.aakrylov.sandbox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/greetings/personal")
                .fullyAuthenticated()

                .antMatchers("/greetings/**")
                .permitAll()

                .antMatchers("/users/**")
                .permitAll()

                .antMatchers("/akka/**")
                .permitAll()

                .and()
                .httpBasic()

                .and()
                .headers()
                .frameOptions()
                .disable();
    }
}
