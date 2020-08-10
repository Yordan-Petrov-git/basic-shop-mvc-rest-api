package com.shop.advance.academy.yordan.petrov.git.shop.configuration;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class configuratoion for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;

    /**
     * @param jwtAuthenticationEntryPoint
     * @param userService
     * @param jwtRequestFilter
     */
    @Autowired
    public ApplicationSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, UserService userService, JwtRequestFilter jwtRequestFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    /**
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user", "/api/login").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/user", "/api/login").anonymous()
                .antMatchers(HttpMethod.DELETE, "/api/items", "/api/cart", "/api/address", "/api/contactinformation", "/api/media", "/api/opinion").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/api/seller", "/api/purchases", "/api/order", "/api/transactions", "/api/items", "/api/cart", "/api/address", "/api/contactinformation", "/api/media", "/api/opinion").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.PUT, "/api/user", "/api/seller", "/api/purchases", "/api/order", "/api/transactions", "/api/items", "/api/cart", "/api/address", "/api/contactinformation", "/api/media", "/api/opinion").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.PATCH, "/api/user", "/api/seller", "/api/purchases", "/api/order", "/api/transactions", "/api/items", "/api/cart", "/api/address", "/api/contactinformation", "/api/media", "/api/opinion").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.GET, "/api/user/**", "/api/seller/**", "/api/purchases", "/api/order/**", "/api/currency/**", "/api/country/**", "/api/city/**", "/api/transactions/**", "/api/card/**", "/api/items/**", "/api/cart/**", "/api/address/**", "/api/contactinformation/**", "/api/media/**", "/api/opinion/**", "/api/user/serach/user/username/**", " /api/user/serach/user/username/like/**", "api/items/serach/item/title/**", "api/items/serach/item/title/like/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.DELETE, "/api/seller", "/api/order").hasAuthority("ROLE_MODERATOR")
                .antMatchers(HttpMethod.GET, "/api/address").hasAuthority("ROLE_MODERATOR")
                .antMatchers(HttpMethod.GET, "/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT, "/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PATCH, "/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.TRACE, "/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.OPTIONS, "/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and().
                exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * @param userService
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> {
            try {
                UserDetails found = userService.loadUserByUsername(username);
                log.debug(">>> User authenticated for username: {} is {}", username, found);
                return found;
            } catch (EntityNotFoundException ex) {
                throw new UsernameNotFoundException(ex.getMessage(), ex);
            }
        };
    }

}

