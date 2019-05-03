package com.paul.farm.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CreateNewAccountReqDto {

    @NotNull(message = "Cannot be empty")
    private String username;

    @NotNull(message = "Cannot be empty")
    private String password;

    @NotNull(message = "Cannot be empty")
    private String confirmPassword;

}
