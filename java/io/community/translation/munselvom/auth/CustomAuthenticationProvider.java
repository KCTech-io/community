package io.community.translation.munselvom.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	MyUserDetailsService myService;
	
	MyUserDetails userDetails;

	
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String username = auth.getName();
		String password = auth.getCredentials().toString();
		
		userDetails = (MyUserDetails) myService.loadUserByUsername(username);
		
		if (userDetails.getPassword().equals(password)) { 
			return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
		}
		else {
			throw new BadCredentialsException("Authentication Failed!");
		}

	}

	@Override
	public boolean supports(Class<?> auth) {

		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
