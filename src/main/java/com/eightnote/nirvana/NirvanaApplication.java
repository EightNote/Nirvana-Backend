package com.eightnote.nirvana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

	@GetMapping("/home")
	public String s(HttpServletResponse response) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
			System.out.println("no user");

			response.sendRedirect("login");
			return "";
		}

		return "Hello user %s with roles %s"
				.formatted(authentication.getName(), authentication.getAuthorities())
				;
	}

	@GetMapping("/logout")
	public ResponseEntity logout(HttpServletResponse response) throws IOException {
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().contains((GrantedAuthority) () -> "ROLE_ANONYMOUS")) {
			System.out.println("Authentication not found");
			response.sendRedirect("login");
		}
		SecurityContextHolder.clearContext();
		System.out.println("Logged Out");
		response.sendRedirect("home");

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/login")
	public String login() {
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
			System.out.println(authentication.getAuthorities().toString());
			System.out.println("Already logged in");
			return "Already logged in";
		}

		SecurityContextHolder.clearContext();
		return "LOGIN PAGE";
	}

	@GetMapping("/user-xyz")
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
