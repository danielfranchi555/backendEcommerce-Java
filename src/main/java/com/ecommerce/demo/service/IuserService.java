package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.UserDto;
import com.ecommerce.demo.error.UserNotFoundException;
import com.ecommerce.demo.models.User;

import java.util.List;

public interface IuserService {
    public List<UserDto> getUsers(); // GET ALL USERS
    public UserDto getUser(Integer id) throws UserNotFoundException; // GET USER
    public void addUser(User user); // ADD USER
    public void deleteUser();
    public void updateUser(Integer id, User user) throws UserNotFoundException;
}
