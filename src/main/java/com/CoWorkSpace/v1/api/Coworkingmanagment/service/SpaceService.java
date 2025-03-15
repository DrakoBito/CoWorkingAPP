package com.CoWorkSpace.v1.api.Coworkingmanagment.service;

import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Space;
import com.CoWorkSpace.v1.api.Coworkingmanagment.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

    /**
     * Retrieves all available spaces.
     *
     * @return A list of all spaces.
     */
    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    /**
     * Retrieves a space by its ID.
     *
     * @param id The ID of the space.
     * @return The found space.
     * @throws IllegalStateException If no space with the given ID is found.
     */
    public Space getSpaceById(Long id) {
        return spaceRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("The requested space was not found."));
    }
}
