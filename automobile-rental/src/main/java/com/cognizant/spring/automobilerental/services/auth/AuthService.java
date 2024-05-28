package com.cognizant.spring.automobilerental.services.auth;

import org.springframework.stereotype.Service;

import com.cognizant.spring.automobilerental.dto.SignupRequest;
import com.cognizant.spring.automobilerental.dto.UserDto;
import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.enums.UserRole;

@Service
public interface AuthService {
	UserDto createCustomer(SignupRequest signupRequest);
	public boolean hasCustomerWithEmail(String email);
	
}
