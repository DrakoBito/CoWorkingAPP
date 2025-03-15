package com.CoWorkSpace.v1.api.Coworkingmanagment.controller;

import com.CoWorkSpace.v1.api.Coworkingmanagment.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.CoWorkSpace.v1.api.Coworkingmanagment.model.Space;

@RestController
@RequestMapping("/spaces")
@RequiredArgsConstructor
public class SpaceController {
    private final SpaceService spaceService;

    /**
     * Retrieves all available spaces.
     * @return List of all spaces.
     */
    @GetMapping
    public ResponseEntity<List<Space>> getAllSpaces() {
        return ResponseEntity.ok(spaceService.getAllSpaces());
    }

    /**
     * Retrieves a specific space by its ID.
     * @param id The ID of the space to retrieve.
     * @return The space corresponding to the given ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Space> getSpaceById(@PathVariable Long id) {
        return ResponseEntity.ok(spaceService.getSpaceById(id));
    }
}
