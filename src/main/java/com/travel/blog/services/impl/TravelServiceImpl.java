package com.travel.blog.services.impl;

import com.travel.blog.exceptions.ResourceNotFoundException;
import com.travel.blog.models.Travel;
import com.travel.blog.payloads.TravelDTO;
import com.travel.blog.repositories.TravelRepository;
import com.travel.blog.services.TravelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelServiceImpl implements TravelService {

    private TravelRepository travelRepository;

    public TravelServiceImpl(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @Override
    public TravelDTO createTravel(TravelDTO travelDTO) {
        Travel travel = convertToEntity(travelDTO);

        //Save Entity
        Travel newTravel = travelRepository.save(travel);

        return convertToDTO(newTravel);
    }

    @Override
    public List<TravelDTO> getAllTravels() {
        List<Travel> travels = travelRepository.findAll();
        return travels.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TravelDTO getTravelById(Long id) {
        //Get travel by id from database
        Travel travel = travelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Travel", "id", id));
        return convertToDTO(travel);
    }

    @Override
    public TravelDTO updateTravel(Long id, TravelDTO travelDTO) {
        Travel travel = travelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Travel", "id", id));

        travel.setTitle(travelDTO.getTitle());
        travel.setDescription(travelDTO.getDescription());
        travel.setDate(travelDTO.getDate());
        travel.setImage(travelDTO.getImage());
        travel.setLocation(travelDTO.getLocation());

        Travel updatedTravel = travelRepository.save(travel);

        return convertToDTO(updatedTravel);
    }

    @Override
    public void deleteTravelById(Long id) {
        Travel travel = travelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Travel", "id", id));
        travelRepository.delete(travel);
    }

    //Convert Entity to DTO
    private TravelDTO convertToDTO(Travel travel) {
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setId(travel.getId());
        travelDTO.setTitle(travel.getTitle());
        travelDTO.setDescription(travel.getDescription());
        travelDTO.setImage(travel.getImage());
        travelDTO.setDate(travel.getDate());
        travelDTO.setLocation(travel.getLocation());

        return travelDTO;
    }

    //Convert DTO to Entity
    private Travel convertToEntity(TravelDTO travelDTO) {
        Travel travel = new Travel();
        travel.setTitle(travelDTO.getTitle());
        travel.setDescription(travelDTO.getDescription());
        travel.setImage(travelDTO.getImage());
        travel.setDate(travelDTO.getDate());
        travel.setLocation(travelDTO.getLocation());

        return travel;
    }
}
