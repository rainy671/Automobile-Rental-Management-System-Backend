package com.cognizant.spring.automobilerental.services.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.spring.automobilerental.dto.SignupRequest;
import com.cognizant.spring.automobilerental.dto.UserDto;
import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.enums.UserRole;
import com.cognizant.spring.automobilerental.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserRepository userRepository;
	@Override
	public UserDto createCustomer(SignupRequest signupRequest) {
		User user = new User();
		user.setName(signupRequest.getName());
		user.setEmail(signupRequest.getEmail());
		 user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
//		user.setPassword(signupRequest.getPassword());
		user.setUserRole(UserRole.CUSTOMER);
		User createdUser = userRepository.save(user);
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		return userDto;
	}

	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
		if (adminAccount == null) {
			User newAdminAccount = new User();
			newAdminAccount.setName("Admin");
			newAdminAccount.setEmail("admin@test.com");
			 newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
//			newAdminAccount.setPassword("admin");
			newAdminAccount.setUserRole(UserRole.ADMIN);
			userRepository.save(newAdminAccount);
			System.out.println("Admin account created successfully!");
		}
	}
	
	
	public boolean hasCustomerWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
}
