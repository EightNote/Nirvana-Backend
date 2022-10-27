package com.eightnote.nirvana.login;

import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.services.NirvanaUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class NirvanaRequestFilter extends OncePerRequestFilter {
    @Autowired
    private NirvanaUserService nirvanaUserService;

    @Autowired
    private JSONWebTokenHandler jsonWebTokenHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get Token form header
//        String token = request.getHeader("Authorization");
//
//        if (token == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String username = jsonWebTokenHandler.getUsernameFromToken(token);
//        if (username == null) {
//            filterChain.doFilter(request, response);;
//            return;
//        }
//
//        NirvanaUser user = nirvanaUserService.loadUserByUsername(username);
//        if (user == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        if (jsonWebTokenHandler.validateToken(token, user)) {
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                    user, null, user.getAuthorities());
//            usernamePasswordAuthenticationToken
//                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            // After setting the Authentication in the context, we specify
//            // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//        filterChain.doFilter(request, response);

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        System.out.println(requestTokenHeader);
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jsonWebTokenHandler.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        //Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            NirvanaUser userDetails = nirvanaUserService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set authentication
            if (jsonWebTokenHandler.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
