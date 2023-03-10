package com.travel.blog.services;

import com.travel.blog.models.User;
import com.travel.blog.payloads.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    void deleteUserById(Long id);

}
