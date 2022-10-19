package com.eightnote.nirvana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/home")
	public String s() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		System.out.println(authentication.getName().toString());
		System.out.println(authentication.getAuthorities().toString());
		System.out.println(authentication.getDetails().toString());
		System.out.println(authentication.getPrincipal().toString());
		return "Hello user %s with roles %s".formatted(authentication.getName(), authentication.getAuthorities());
	}

//	@GetMapping("/logout")
//	public String logout() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		authentication.setAuthenticated(false);
//		System.out.println(authentication.isAuthenticated());
//		return "redirect:home";
//	}

	@GetMapping("/user")
	public String s(
			@RequestParam("name") String username,
			@RequestParam("pass") String password,
			@RequestParam("role") String role
	) {
		var user = User.withUsername(username)
				.password(bCryptPasswordEncoder.encode(password))
				.roles("ARTIST")
				.authorities(role)
				.build();

		userDetailsManager.createUser(user);

		return "Created user %s with roles %s".formatted(user.getUsername(), user.getAuthorities());
	}
}
