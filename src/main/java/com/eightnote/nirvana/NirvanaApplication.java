package com.eightnote.nirvana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;

@SpringBootApplication
@ComponentScan({
		"com.eightnote.nirvana.controllers",
		"com.eightnote.nirvana.services",
		"com.eightnote.nirvana.models",
		"com.eightnote.nirvana.row_mappers",
		"com.eightnote.nirvana.DAOs",
		"com.eightnote.nirvana.login"
})
@RestController
public class NirvanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NirvanaApplication.class, args);
	}

	@GetMapping("/home")
	public String s() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		System.out.println(authentication.getName().toString());
		System.out.println(authentication.getAuthorities().toString());
		System.out.println(authentication.getDetails().toString());
		System.out.println(authentication.getPrincipal().toString());
		return "Hello";
	}
}
