package com.ecomm.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecomm.config.JwtProvider;
import com.ecomm.exception.UserException;
import com.ecomm.repository.UserRepository;
import com.ecomm.request.LoginRequest;
import com.ecomm.response.AuthResponse;
import com.ecomm.service.CustomUserServiceImplmentation;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JwtProvider jwtprovider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userReposotry;
	
	@Autowired
	private CustomUserServiceImplmentation customUserService;
	
	@PostMapping("/signUp")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody com.ecomm.modal.User user) throws UserException
	{	
		String email=user.getEmail();
		String password=user.getPassword();
		String firstString=user.getFirstName();
		String lastString=user.getLastName();
		
		com.ecomm.modal.User isEmailExist=userReposotry.findByEmail(email);
		if(isEmailExist!=null)
		{
			throw new UserException("Email is already used with another account");
		}
		
		com.ecomm.modal.User createdUser=new com.ecomm.modal.User();                                                
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstString);
		createdUser.setLastName(lastString);
		
		com.ecomm.modal.User saveduser=userReposotry.save(createdUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(saveduser.getEmail(),saveduser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=jwtprovider.generateToken(authentication);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("SignUp Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest)
	{
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authenticate(username,password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtprovider.generateToken(authentication);
		AuthResponse authResponse=new AuthResponse(token,"signIn success");
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	
	}

	private Authentication authenticate(String username, String password) 
	{
		UserDetails userDetails=customUserService.loadUserByUsername(username);
		if(userDetails==null)
		{
			throw new BadCredentialsException("invalid username.....");	
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) 
		{
			throw new BadCredentialsException("invalid password.....");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
	
	
}
