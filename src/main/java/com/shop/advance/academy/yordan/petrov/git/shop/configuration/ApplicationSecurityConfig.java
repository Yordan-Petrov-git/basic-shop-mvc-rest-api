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
 * Class configuratoion for SPRING security.
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

    /**
     * Constants for standart user role
     */
    private static final String ROLE_STANDARD_USER = "ROLE_USER";

    /**
     * Constants for moderator user role
     */
    private static final String ROLE_MODERATOR = "ROLE_MODERATOR";

    /**
     * Constants for administrator user role
     */
    private static final String ROLE_ADMIN = "ROLE_ADMIN";


    /**
     * Constants for ADMIN user
     */
    private static final String[] AUTHORISATION_ADMIN_USER = {
            "/**"
    };

    /**
     * Constants for SWAGGER USER INTERFACE
     */
    private static final String[] AUTHORISATION_WHITELIST_SWAGGER = {
            "/swagger-resources/**"
            , "/swagger-ui.html"
            , "/v2/api-docs"
            , "/webjars/**"

    };

    private static final String[] AUTHORISATION_WHITELIST_ACTUATOR = {
            "/actuator/**"
    };

    /**
     * Constants for anonimus user
     */
    private static final String[] AUTHORISATION_ANONIMUS_USER = {
            "/api/user"
            , "/api/login"
    };

    /**
     * Constants for anonimus user
     */
    private static final String[] AUTHORISATION_ANONIMUS_USER_POST_HTTP = {
            "/api/user"
            , "/api/login"
    };

    /**
     * Constants for moderator DELETE HTTP METHOD
     */
    private static final String[] AUTHORISATION_MODERATOR_DELETE_HTTP = {
            "/api/seller", "/api/order"
    };

    /**
     * Constants for moderator GET HTTP METHOD
     */
    private static final String[] AUTHORISATION_MODERATOR_GET_HTTP = {
            "/api/address"
    };

    /**
     * Constants for user DELETE HTTP METHOD
     */
    private static final String[] AUTHORISATION_USER_DELETE_HTTP = {
            "/api/items", "/api/cart", "/api/address"
            , "/api/contactinformation", "/api/media", "/api/opinion"
    };

    /**
     * Constants for user POST HTTP METHOD
     */
    private static final String[] AUTHORISATION_USER_POST_HTTP = {
            "/api/seller", "/api/purchases", "/api/order"
            , "/api/transactions", "/api/items", "/api/cart"
            , "/api/address", "/api/contactinformation", "/api/media"
            , "/api/opinion"
    };

    /**
     * Constants for user POST HTTP METHOD
     */
    private static final String[] AUTHORISATION_USER_PUT_HTTP = {
            "/api/user", "/api/seller", "/api/purchases"
            , "/api/order", "/api/transactions", "/api/items"
            , "/api/cart", "/api/address", "/api/contactinformation"
            , "/api/media", "/api/opinion"
    };

    /**
     * Constants for user PATCH HTTP METHOD
     */
    private static final String[] AUTHORISATION_USER_PATCH_HTTP = {
            "/api/user", "/api/seller", "/api/purchases"
            , "/api/order", "/api/transactions", "/api/items"
            , "/api/cart", "/api/address", "/api/contactinformation"
            , "/api/media", "/api/opinion"
    };

    /**
     * Constants for user GET HTTP METHOD
     */
    private static final String[] AUTHORISATION_USER_GET_HTTP = {
            "/api/user/**/", "/api/seller/**", "/api/purchases"
            , "/api/order/**", "/api/currency/**", "/api/country/**"
            , "/api/city/**", "/api/transactions/**", "/api/card/**"
            , "/api/items/**", "/api/cart/**", "/api/address/**"
            , "/api/contactinformation/**", "/api/media/**", "/api/opinion/**"
            , "/api/user/serach/user/username/**"
            , " /api/user/serach/user/username/like/**"
            , "api/items/serach/item/title/**"
            , "api/items/serach/item/title/like/**"
    };

    /**
     *
     */
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
     * Method for HTTP security configuration.
     *
     * @param httpSecurity HTTP security filter chain configuration.
     * @throws Exception exception to pass.
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, AUTHORISATION_ANONIMUS_USER_POST_HTTP).permitAll()
                .antMatchers(AUTHORISATION_WHITELIST_SWAGGER).permitAll()
                .antMatchers(AUTHORISATION_WHITELIST_ACTUATOR).permitAll()
                .antMatchers(AUTHORISATION_ANONIMUS_USER).anonymous()
                .antMatchers(HttpMethod.DELETE, AUTHORISATION_USER_DELETE_HTTP).hasAnyAuthority(ROLE_STANDARD_USER)
                .antMatchers(HttpMethod.POST, AUTHORISATION_USER_POST_HTTP).hasAnyAuthority(ROLE_STANDARD_USER)
                .antMatchers(HttpMethod.PUT, AUTHORISATION_USER_PUT_HTTP).hasAnyAuthority(ROLE_STANDARD_USER)
                .antMatchers(HttpMethod.PATCH, AUTHORISATION_USER_PATCH_HTTP).hasAnyAuthority(ROLE_STANDARD_USER)
                .antMatchers(HttpMethod.GET, AUTHORISATION_USER_GET_HTTP).hasAnyAuthority(ROLE_STANDARD_USER)
                .antMatchers(HttpMethod.DELETE, AUTHORISATION_MODERATOR_DELETE_HTTP).hasAuthority(ROLE_MODERATOR)
                .antMatchers(HttpMethod.GET, AUTHORISATION_MODERATOR_GET_HTTP).hasAuthority(ROLE_MODERATOR)
                .antMatchers(HttpMethod.GET, AUTHORISATION_ADMIN_USER).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, AUTHORISATION_ADMIN_USER).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, AUTHORISATION_ADMIN_USER).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PATCH, AUTHORISATION_ADMIN_USER).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.TRACE, AUTHORISATION_ADMIN_USER).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.OPTIONS, AUTHORISATION_ADMIN_USER).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, AUTHORISATION_ADMIN_USER).hasAuthority(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and().
                exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Bean for username and password.
     *
     * @param userService
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> {
            try {
                UserDetails found = userService.loadUserByUsername(username);
                log.debug("User authenticated for username: {} is {}", username, found);
                return found;
            } catch (EntityNotFoundException ex) {
                throw new UsernameNotFoundException(ex.getMessage(), ex);
            }
        };
    }

}

