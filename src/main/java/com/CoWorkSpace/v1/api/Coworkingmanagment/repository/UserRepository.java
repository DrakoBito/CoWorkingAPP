package com.CoWorkSpace.v1.api.Coworkingmanagment.repository;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email.
     * @param email the email of the user to find.
     * @return an Optional containing the found user, or empty if no user exists.
     */
    Optional<User> findByEmail(String email);
}
