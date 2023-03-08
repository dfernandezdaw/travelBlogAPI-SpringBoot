package com.travel.blog.controllers;

import com.travel.blog.payloads.TravelDTO;
import com.travel.blog.services.TravelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travels")
public class TravelController {
    private TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    //Create travel
    @PostMapping
    public ResponseEntity<TravelDTO> createTravel(@RequestBody TravelDTO travelDTO) {
        return new ResponseEntity<>(travelService.createTravel(travelDTO), HttpStatus.CREATED);
    }

    //Get all travels
    @GetMapping
    public List<TravelDTO> getAllTravels() {
        return travelService.getAllTravels();
    }

    //Get travel by id
    @GetMapping("/{id}")
    public ResponseEntity<TravelDTO> getTravelById(@PathVariable Long id) {
        return ResponseEntity.ok(travelService.getTravelById(id));
    }

    //Update travel by id
    @PutMapping("/{id}")
    public ResponseEntity<TravelDTO> updateTravel(@PathVariable Long id, @RequestBody TravelDTO travelDTO) {
        TravelDTO updatedTravel = travelService.updateTravel(id, travelDTO);
        return new ResponseEntity<>(updatedTravel, HttpStatus.OK);
    }

    //Delete travel by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTravelById(@PathVariable Long id) {
        travelService.deleteTravelById(id);
        return new ResponseEntity<>("Travel with id " + id + " was deleted", HttpStatus.OK);
    }
}
