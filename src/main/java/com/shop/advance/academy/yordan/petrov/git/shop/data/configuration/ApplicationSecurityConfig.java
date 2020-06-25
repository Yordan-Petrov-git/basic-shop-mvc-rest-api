package com.shop.advance.academy.yordan.petrov.git.shop.data.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/users/register","/users/login").anonymous()
                .antMatchers(HttpMethod.POST, "/api/users/register").permitAll();
//                .antMatchers(HttpMethod.POST, "/**")
 //               .hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.PUT, "/**")
//                .hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated();
//                .and()
//                .formLogin()
//                .loginPage("/users/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/home")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/");

    }



}

