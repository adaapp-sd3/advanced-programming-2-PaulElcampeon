package com.paul.farm.models.users;

import com.paul.farm.dtos.CreateNewAccountReqDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "USERS")
public class User {
    @Id
    private String username;
    private String password;
    private List<String> roles = Arrays.asList("USER");

    public User() {}

    public User(CreateNewAccountReqDto createNewAccountReqDto) {
        this.username = createNewAccountReqDto.getUsername();
        this.password = createNewAccountReqDto.getPassword();
    }
}
