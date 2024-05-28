package com.cognizant.spring.automobilerental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findFirstByEmail(String email);
	User findByUserRole(UserRole userRole);

}
