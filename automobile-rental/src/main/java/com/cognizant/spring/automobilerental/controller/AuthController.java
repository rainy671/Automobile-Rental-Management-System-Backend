package com.cognizant.spring.automobilerental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.cognizant.spring.automobilerental.dto.AuthenticationRequest;
import com.cognizant.spring.automobilerental.dto.AuthenticationResponse;
import com.cognizant.spring.automobilerental.dto.SignupRequest;
import com.cognizant.spring.automobilerental.dto.UserDto;
import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.repository.UserRepository;
import com.cognizant.spring.automobilerental.services.auth.AuthService;
import com.cognizant.spring.automobilerental.services.jwt.UserService;
import com.cognizant.spring.automobilerental.utils.JwtUtil;





	@RestController
	@RequestMapping("/api/auth")
	@RequiredArgsConstructor
	public class AuthController {

		  private final AuthService authService;	
		  private final AuthenticationManager authenticationManager;
		  private final  UserService userService; 
		  private final JwtUtil jwtUtil; 
		  private final  UserRepository userRepository;
		 

		@PostMapping("/signup")

		public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
			if (authService.hasCustomerWithEmail(signupRequest.getEmail()))
				return new ResponseEntity<>("Customer already exists with this email.", HttpStatus.NOT_ACCEPTABLE);
			UserDto createdCustomerDto = authService.createCustomer(signupRequest);
			if (createdCustomerDto == null)
				return new ResponseEntity<>("Customer not created. Come again later.", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
		}
		
		@PostMapping("/login") 
		  public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, DisabledException, UsernameNotFoundException{
			  try { 
				  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( authenticationRequest.getEmail(),authenticationRequest.getPassword())); 
				  } 
			  catch (BadCredentialsException e) {
				  throw new BadCredentialsException("Incorrect username or password. Please try again.");
			  } 
			  final UserDetails userDetails = userService.userDetailsService()
					  .loadUserByUsername(authenticationRequest.getEmail()); 
			  Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername()); 
			  final String jwt  = jwtUtil.generateToken(userDetails);
			  AuthenticationResponse authenticationResponse = new AuthenticationResponse(); 
			  if(optionalUser.isPresent()) { 
				  authenticationResponse.setJwt(jwt);
				  authenticationResponse.setUserId(optionalUser.get().getId());
				  authenticationResponse.setUserRole(optionalUser.get().getUserRole()); 
			  }
			  	return authenticationResponse; 
			  }

}
