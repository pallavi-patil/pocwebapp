package com.capgemini.pocwebapp.spring.config.security.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class WebAppAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;

	private Log log = LogFactory.getLog(WebAppAuthenticationProvider.class.getName());

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		String username = (String) authToken.getPrincipal();
		String rawPassword = (String) authToken.getCredentials();

		WebAppAuthenticationDetails details = (WebAppAuthenticationDetails) authentication.getDetails();

		if (log.isInfoEnabled())
			log.info("Attempting to authenticate user: " + username + ", from host: " + details.getRemoteHost());

		UsernamePasswordAuthenticationToken token = null;

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (userDetails != null) {
			boolean isValidPassword = validatePassword(userDetails, rawPassword);
			if (isValidPassword) {
				token = handleValidPassword(userDetails, details);
			} else {
				token = handleInvalidPassword(userDetails, details);
			}
		}
		return token;
	}

	private boolean validatePassword(UserDetails userDetails, String rawPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(rawPassword, userDetails.getPassword());
	}

	/**
	 * Create auth token after password is validated. This will check for locked,
	 * inactive, etc. and throw an exception if the login needs to be stopped.
	 */
	private UsernamePasswordAuthenticationToken handleValidPassword(UserDetails accountEntity,
			WebAppAuthenticationDetails details) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountEntity,
				accountEntity.getPassword(), accountEntity.getAuthorities());
		token.setDetails(details);
		return token;

	}

	/**
	 * Handle password failure and create auth token for a failed password
	 * validation.
	 */
	private UsernamePasswordAuthenticationToken handleInvalidPassword(UserDetails accountEntity,
			WebAppAuthenticationDetails details) throws AuthenticationException {
		// TODO: does this get a granted auth????

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountEntity, "",
				accountEntity.getAuthorities());
		try {
			token.setDetails(details);
			SecurityContextHolder.getContext().setAuthentication(token);
			// recordLoginFailure(accountEntity);
		} finally {
			SecurityContextHolder.getContext().setAuthentication(null);
			token = null;
		}
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
