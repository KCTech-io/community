package io.community.translation.munselvom.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserList, String> {
	
	    public <Optional> UserList findByUserName(String username);
	    
	    public <Optional> UserList findByEmailId(String emailid);

}
