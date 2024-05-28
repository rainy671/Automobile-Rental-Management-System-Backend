package com.cognizant.spring.automobilerental.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.spring.automobilerental.entity.User;
import com.cognizant.spring.automobilerental.enums.UserRole;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindFirstByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setUserRole(UserRole.CUSTOMER);
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findFirstByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testFindByUserRole() {
        User user = new User();
        user.setEmail("admin@example.com");
        user.setPassword("password");
        user.setUserRole(UserRole.ADMIN);
        userRepository.save(user);

        User foundUser = userRepository.findByUserRole(UserRole.ADMIN);

        assertNotNull(foundUser);
        assertEquals(UserRole.ADMIN, foundUser.getUserRole());
    }
    
    @Test
    public void testFindFirstByEmailWhenUserDoesNotExist() {
        Optional<User> foundUser = userRepository.findFirstByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());
    }
}
