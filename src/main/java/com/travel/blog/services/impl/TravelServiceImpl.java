package com.travel.blog.services.impl;

import com.travel.blog.models.Travel;
import com.travel.blog.payloads.TravelDTO;
import com.travel.blog.repositories.TravelRepository;
import com.travel.blog.services.TravelService;
import org.springframework.stereotype.Service;

@Service
public class TravelServiceImpl implements TravelService {

    private TravelRepository travelRepository;

    public TravelServiceImpl(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @Override
    public TravelDTO createTravel(TravelDTO travelDTO) {
        //Convert DTO to Entity
        Travel travel = new Travel();
        travel.setTitle(travelDTO.getTitle());
        travel.setDescription(travelDTO.getDescription());
        travel.setImage(travelDTO.getImage());
        travel.setDate(travelDTO.getDate());
        travel.setLocation(travelDTO.getLocation());

        //Save Entity
        Travel newTravel = travelRepository.save(travel);

        //Convert Entity to DTO
        TravelDTO travelResponse = new TravelDTO();
        travelResponse.setId(newTravel.getId());
        travelResponse.setTitle(newTravel.getTitle());
        travelResponse.setDescription(newTravel.getDescription());
        travelResponse.setImage(newTravel.getImage());
        travelResponse.setDate(newTravel.getDate());
        travelResponse.setLocation(newTravel.getLocation());

        return travelResponse;
    }
}
