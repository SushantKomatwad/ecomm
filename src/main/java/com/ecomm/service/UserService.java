package com.ecomm.service;

import com.ecomm.exception.UserException;
import com.ecomm.modal.User;

public interface UserService {
	
	public User findUserById(Long userid) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	

}
