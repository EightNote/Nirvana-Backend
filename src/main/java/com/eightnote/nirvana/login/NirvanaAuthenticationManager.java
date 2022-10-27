package com.eightnote.nirvana.login;

import com.eightnote.nirvana.services.NirvanaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class NirvanaAuthenticationManager implements AuthenticationManager {
    @Autowired
    private NirvanaUserService nirvanaUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String rawPassword = authentication.getCredentials().toString();
        String encryptedPassword = nirvanaUserService.loadUserByUsername(authentication.getName()).getPassword();

        if (new BCryptPasswordEncoder().matches(rawPassword, encryptedPassword)) {
            return authentication;
        }

        return null;
    }
}