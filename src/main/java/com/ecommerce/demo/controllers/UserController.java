package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dto.UserDto;
import com.ecommerce.demo.error.UserNotFoundException;
import com.ecommerce.demo.models.User;
import com.ecommerce.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //GET ALL USERS
    @GetMapping("/users")
    public List<UserDto> users(){
        return userService.getUsers();
    }

    //GET USER
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable Integer id) throws UserNotFoundException {
     return userService.getUser(id);
    }

    //ADD USER
    @PostMapping("/users")
    public void addUser(@Valid @RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Integer id , @RequestBody User user) throws UserNotFoundException{
     userService.updateUser(id,user);
    }

}
