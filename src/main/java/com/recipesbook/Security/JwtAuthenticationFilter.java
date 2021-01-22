package com.recipesbook.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = getJwtFromRequest(request);

		/* if the token is valid , the request could be forwarded to the controller */
		if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
			
			/* now jwt is validated 
			 * let's retreive the username from the jwt */
			String username = jwtProvider.getUsernameFromJWT(jwt);	
			
			/* then the userdetail from the usename */
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			/* then store that user in the securitycontext */
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
	
		/* the token is sent as part of the auth header following the bearer schema */
		String bearerToken = request.getHeader("Authorization");

		/* so let's get it by retriving the bearer token(jwt) from the HttpServletRequest*/ 
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return bearerToken;
	}
}
