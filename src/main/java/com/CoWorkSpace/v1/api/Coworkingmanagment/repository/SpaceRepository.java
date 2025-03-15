package com.CoWorkSpace.v1.api.Coworkingmanagment.repository;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Space entities.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    // No additional methods are needed for now since JpaRepository provides all necessary CRUD operations.
}
