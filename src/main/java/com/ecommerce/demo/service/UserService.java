package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.UserDto;
import com.ecommerce.demo.error.UserNotFoundException;
import com.ecommerce.demo.models.User;
import com.ecommerce.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IuserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        Iterable<User> usersFromDb = userRepository.findAll();
        List<UserDto>listUser = new ArrayList<>();
        for (User usr : usersFromDb) {
            UserDto userDto = new UserDto();
            userDto.setName(usr.getName());
            userDto.setLast_name(usr.getLast_name());
            userDto.setAddress(usr.getAddress());
            userDto.setPhone(usr.getPhone());
            userDto.setEmail(usr.getEmail());
            listUser.add(userDto);
        }

        return  listUser;
    }

    @Override
    public UserDto getUser(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User is not available");
        }
        UserDto userDto = new UserDto();
        userDto.setName(user.get().getName());
        userDto.setLast_name(user.get().getLast_name());
        userDto.setAddress(user.get().getAddress());
        userDto.setEmail(user.get().getEmail());
        userDto.setPhone(user.get().getPhone());

        return userDto;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void updateUser(Integer id, User user) throws UserNotFoundException {
        Optional<User> userFound = userRepository.findById(id);
        if (!userFound.isPresent()) {
            throw new UserNotFoundException("User is not available");
        }

        // Actualizar el usuario existente con los nuevos valores
        User existingUser = userFound.get();
        existingUser.setName(user.getName());
        existingUser.setLast_name(user.getLast_name());
        existingUser.setAddress(user.getAddress());
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        // Guardar los cambios en la base de datos
        userRepository.save(existingUser);
    }
}
