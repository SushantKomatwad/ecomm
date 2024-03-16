package com.ecomm;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ecomm.config.JwtProvider;
import com.ecomm.controller.AuthController;

@SpringBootTest
class EcommbuyAgainApplicationTests {

	@Autowired
    private AuthController authController; // Assuming you have an AuthController class

	@Autowired
	private JwtProvider jwtprovider;
	
	@Test
	public void testSignUpEndpoint() {
	    // Create a dummy Authentication object
	    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
	    Authentication authentication = new UsernamePasswordAuthenticationToken(
	            new User("dummyuser", "dummypassword", Collections.singleton(authority)),
	            null,
	            Collections.singleton(authority)
	    );

	    // Generate a token using the JwtProvider
	    String token = jwtprovider.generateToken(authentication);
	    
	    // Now, you can include this token in the Authorization header of your request to /auth/signUp
	    // For example, in Postman, add a header: Key=Authorization, Value=Bearer <your_generated_token>
	}

}
