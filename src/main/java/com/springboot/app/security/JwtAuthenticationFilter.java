package com.springboot.app.security;

//import com.springboot.blog.security.JwtTokenProvider;
//import com.springboot.blog.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // inject dependencies
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    	try {
    		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            }
    		System.out.println("++++++++++222++++++++++123++++++++");
    		logger.debug("_______________________________________");
    		// get JWT (token) from http request
            String token = getJWTfromRequest(request);
            // validate token
            if(StringUtils.hasText(token) && tokenProvider.validateToken(token)){
                // get username from token
                String username = tokenProvider.getUsernameFromJWT(token);
                // load user associated with token
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // set spring security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else {
//            	((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");	
            }
            filterChain.doFilter(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error in server");
		}
        
    }

    // Bearer <accessToken>
    private String getJWTfromRequest(HttpServletRequest request){
            String bearerToken = request.getHeader("Authorization");
            if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
                return bearerToken.substring(7, bearerToken.length());
            }
            return null;
    }

}
