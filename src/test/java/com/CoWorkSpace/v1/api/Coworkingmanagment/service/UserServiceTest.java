package com.CoWorkSpace.v1.api.Coworkingmanagment.service;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import com.CoWorkSpace.v1.api.Coworkingmanagment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_ShouldThrowException_WhenEmailIsTaken() {
        // simuling the user already exists
        String email = "juan123@gmail.com";
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(new User("Test User", email, "password")));

        // verify that the method throws an exception
        assertThrows(IllegalStateException.class, () -> {
            userService.registerUser("Test User", email, "password");
        });
    }

    @Test
    void testRegisterUser_ShouldSavedUser_WhenEmailNotTaken() {
        //simuling the user does not exist
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // simuling the repository save method
        when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //simuling the password encoder
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_password");

        //execute the method to test
        User user = userService.registerUser("New User", email, "password");

        //verify that the user was saved
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals("hashed_password", user.getPassword()); // Verifica que se usó el encriptador
    }
}
