package com.cognizant.spring.automobilerental.dto;

import com.cognizant.spring.automobilerental.enums.UserRole;

import lombok.Data;

@Data

public class AuthenticationResponse {

	  private String jwt;
	    private UserRole userRole;
	    private Long userId;
}

