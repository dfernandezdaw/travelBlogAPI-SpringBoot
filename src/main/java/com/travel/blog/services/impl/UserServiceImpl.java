package com.travel.blog.services.impl;

import com.travel.blog.exceptions.ResourceNotFoundException;
import com.travel.blog.models.User;
import com.travel.blog.payloads.UserDTO;
import com.travel.blog.repositories.UserRepository;
import com.travel.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper mapper;
    private UserRepository userRepository;

    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User newUser = userRepository.save(user);
        return convertToDTO(newUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    private User convertToEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    private UserDTO convertToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
}
