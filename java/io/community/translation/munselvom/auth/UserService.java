package io.community.translation.munselvom.auth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	AuthenticationProvider authProvider;
	
	public UserList getUserByName(String username) {
		
		Optional<UserList> user = Optional.ofNullable(userRepository.findByUserName(username));
		
		if (user.isPresent()) {
		
		return user.get();
		}
		else 
			return null;
		
	}
	public UserList getUserByEmail(String email) {
		
		Optional<UserList> user = Optional.ofNullable(userRepository.findByEmailId(email));
		
		if (user.isPresent()) {
		
		return user.get();
		}
		else 
			return null;
		
	}
	
	public UserList createUserByForm(UserForm userForm) throws ParseException {
		UserList user = new UserList();
		user.setUserName(userForm.getUserId());
		//user.setUserdesc(userForm.getUsetemp());
		user.setUserdesc(userForm.getUsername());
		user.setActive(true); //Set default to True
		user.setEmailid(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setGender(userForm.getGender());
		
		System.out.println("Dob Txt"+userForm.getDobtext());
		
		Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(userForm.getDobtext());
		
		user.setDob(dob);
		
		user.setRole("ROLE_USER"); //Default role from signup user is USER_ROLE
		user.setReason(userForm.getReason());
		user.setSelf(userForm.getSelf());
		
		addUser(user);
		
		return user;
	}

	public void addUser(UserList user) {
		userRepository.save(user);
		
	}
	
	public UserList getAuthStatus() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		if (auth.isAuthenticated()) {
		
			String authUser = auth.getName();
			return getUserByName(authUser);
		}
		
		return null;

		
	}
	
	public String userLogin(String username, String password) {

		try {
	        
	        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
	        
	        authProvider.authenticate(auth);
	        
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        
	        System.out.println("success autoLogin");
	        return "Success";
	    } catch (Exception e) {
	        SecurityContextHolder.getContext().setAuthentication(null);
	        System.out.println("Failure in autoLogin "+ e);
	        return "Invalid User Id/Password!";
	    }

	}
	public void userLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
	}

	public void updateSession() {
	ServletRequestAttributes attr = (ServletRequestAttributes) 
		    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); // true == allow create
	}

	
}
