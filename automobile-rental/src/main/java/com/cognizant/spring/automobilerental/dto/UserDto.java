package com.cognizant.spring.automobilerental.dto;

import com.cognizant.spring.automobilerental.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String name;
	private String email;
	private UserRole userRole;

}
