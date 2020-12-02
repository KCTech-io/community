package io.community.translation.munselvom.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	/**
	 * 
	 */
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<UserList> user = Optional.ofNullable(userRepository.findByUserName(userid));
		
		user.orElseThrow(()-> new UsernameNotFoundException("Not Found user "+userid));
		
		return user.map(MyUserDetails::new).get();
		
	}



}

