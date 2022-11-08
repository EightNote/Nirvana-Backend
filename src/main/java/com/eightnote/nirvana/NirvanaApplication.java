package com.eightnote.nirvana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
@ComponentScan({
		"com.eightnote.nirvana.controllers",
		"com.eightnote.nirvana.services",
		"com.eightnote.nirvana.models",
		"com.eightnote.nirvana.row_mappers",
		"com.eightnote.nirvana.DAOs",
		"com.eightnote.nirvana.login",
		"org.springframework.security.provisioning.JdbcUserDetailsManager"
})
@RestController
public class NirvanaApplication {
	private JdbcUserDetailsManager userDetailsManager;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	public static void main(String[] args) {
		SpringApplication.run(NirvanaApplication.class, args);
	}
}
