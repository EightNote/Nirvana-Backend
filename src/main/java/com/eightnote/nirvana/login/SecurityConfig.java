package com.eightnote.nirvana.login;//package com.eightnote.nirvana.login;
//
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jdbcUserDetailsManager()).passwordEncoder(passwordEncoder());
        System.out.println("INITIALISED SECURITY");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/home", "/login").permitAll();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/401/");
        // http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        // http.authenticationProvider(authenticationProvider());
        http.authorizeRequests()
//                .and().formLogin().loginPage("/login/").defaultSuccessUrl("/loggedin/").failureUrl("/login-error/").usernameParameter("username").passwordParameter("password")
        .and().logout()
        .logoutUrl("/logout/").logoutSuccessUrl("/loggedout/");
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);

        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;

//@Configuration
//@Component
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    // @Bean
//    // public AuthenticationProvider authenticationProvider() {
//    // DaoAuthenticationProvider authenticationProvider = new
//    // DaoAuthenticationProvider();
//    // authenticationProvider.setUserDetailsService(userDetailsService);
//    // authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//    // authenticationProvider.setHideUserNotFoundExceptions(false);
//    // return authenticationProvider;
//    // }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/401/");
//        // http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//        // http.authenticationProvider(authenticationProvider());
//        http.authorizeRequests().and().formLogin().loginPage("/login/").defaultSuccessUrl("/loggedin/")
//                .failureUrl("/login-error/").usernameParameter("username").passwordParameter("password").and().logout()
//                .logoutUrl("/logout/").logoutSuccessUrl("/loggedout/");
//    }
//
//    // @Bean
//    // @Override
//    // public AuthenticationManager authenticationManagerBean() throws Exception {
//    // return super.authenticationManagerBean();
//    // }
//}