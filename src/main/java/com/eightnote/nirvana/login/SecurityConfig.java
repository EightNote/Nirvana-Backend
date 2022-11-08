package com.eightnote.nirvana.login;

import com.eightnote.nirvana.services.NirvanaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private NirvanaRequestFilter nirvanaRequestFilter;

    @Autowired
    private NirvanaAuthEntryPoint nirvanaAuthEntryPoint;

    @Autowired
    private NirvanaUserService nirvanaUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(nirvanaUserDetailsManager()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/user/sign-up/", "/user/sign-up/artist/", "/user/sign-in/*", "/trending/*").permitAll()
//                .antMatchers("/home", "/user/sign-in/*", "/login","/countries/all/","/genre/*" ).permitAll()
//                .anyRequest().authenticated().and().
                .anyRequest().permitAll().and().
                exceptionHandling().authenticationEntryPoint(nirvanaAuthEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(nirvanaRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public NirvanaUserService nirvanaUserDetailsManager() {
        return nirvanaUserService;
    }

    @Override
    public NirvanaAuthenticationManager authenticationManagerBean() throws Exception {
        return new NirvanaAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}