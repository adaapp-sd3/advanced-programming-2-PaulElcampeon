package com.paul.farm.services;

import com.paul.farm.dtos.CreateNewAccountReqDto;
import com.paul.farm.repositories.UserRepository;
import com.paul.farm.services.interfaces.UserService;
import com.paul.farm.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createNewUser(CreateNewAccountReqDto createNewAccountReqDto) {
        createNewAccountReqDto.setPassword(passwordEncoder.encode(createNewAccountReqDto.getPassword()));
        userRepository.insert(new User(createNewAccountReqDto));
    }

    @Override
    public User getUser(String username) {
        return userRepository.findById(username).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return userRepository.existsById(username);
    }
}
