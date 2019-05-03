package com.paul.farm.services.interfaces;


import com.paul.farm.dtos.CreateNewAccountReqDto;
import com.paul.farm.models.users.User;

public interface UserService {

    void createNewUser(CreateNewAccountReqDto createNewAccountReqDto);

    User getUser(String username);

    void updateUser(User user);

    void deleteUser(String username);

    boolean checkIfUserExists(String username);
}
