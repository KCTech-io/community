package io.community.translation.munselvom.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomAuthenticationProvider.class)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	CustomAuthenticationProvider customAuth;
	@Autowired
	UserDetailsService userDetailsService;


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/translate").hasRole("USER")
			.antMatchers("/").permitAll()
			/*
			.exceptionHandling(e -> e
					.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))) */
			.and()
			.formLogin();
			
			/*.failureHandler(authenticationFailureHandler());
		
			http.exceptionHandling().authenticationEntryPoint(myEntryPoint);
			*/
        
	}
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder;
	}
	*/
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() { 
		return NoOpPasswordEncoder.getInstance(); 
		}
}