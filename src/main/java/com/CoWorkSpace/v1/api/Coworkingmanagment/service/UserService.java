package com.CoWorkSpace.v1.api.Coworkingmanagment.service;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import com.CoWorkSpace.v1.api.Coworkingmanagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user in the system.
     *
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param password The raw password of the user.
     * @return The newly created user.
     * @throws IllegalStateException If the email is already in use.
     */
    @Transactional
    public User registerUser(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("Email is already taken.");
        }
        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encryptedPassword);
        return userRepository.save(user);
    }

    /**
     * Retrieves all registered users.
     *
     * @return A list of all users.
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user.
     * @return The user found with the given email.
     * @throws IllegalStateException If no user is found.
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found."));
    }
}
