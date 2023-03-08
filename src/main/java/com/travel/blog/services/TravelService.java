package com.travel.blog.services;

import com.travel.blog.payloads.TravelDTO;

import java.util.List;

public interface TravelService {
    TravelDTO createTravel(TravelDTO travelDTO);

    List<TravelDTO> getAllTravels();

    TravelDTO getTravelById(Long id);

    TravelDTO updateTravel(Long id, TravelDTO travelDTO);

    void deleteTravelById(Long id);
}
