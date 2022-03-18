package com.example.examfinal.config;

import com.example.examfinal.model.entity.enums.UserRoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/", "/map","/users/login", "/users/register","/cities/all","/cities/details",
                        "/cities/{id}/details","/places/all", "/places/{id}/place-details", "/places/culture", "/places/nature",
                        "/places/history", "/places/religious", "/api/{placeId}/comments(placeId=${place.id})").permitAll().
                antMatchers("/places/add", "/statistics", "/cities/add","/cities/{id}/edit", "/cities/{id}/edit/errors", "/users/{id}/user-details", "/users/all","/places/{id}/edit/errors").hasRole(UserRoleEnum.ADMIN.name()).
                antMatchers(  "/places/{id}/edit/errors", "/places/add").hasRole(UserRoleEnum.USER.name()).
                antMatchers("/**").authenticated().
                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/places/all", true).
                failureForwardUrl("/users/login-error").
                and().
                logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/cities/all").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);

    }
}
