package com.paul.farm.controllers;

import com.paul.farm.dtos.CreateNewAccountReqDto;
import com.paul.farm.services.interfaces.FarmService;
import com.paul.farm.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class CreateNewAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private FarmService farmService;

    private Logger loggger = Logger.getLogger(CreateNewAccountController.class.getName());

    @RequestMapping(value = "/create-account", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute @Valid CreateNewAccountReqDto createNewAccountReqDto, BindingResult result, Model model) {

        if (userService.checkIfUserExists(createNewAccountReqDto.getUsername())) {
            return "redirect:/create-account?error";
        }

        if (result.hasErrors() || userService.checkIfUserExists(createNewAccountReqDto.getUsername())) {
            loggger.log(Level.INFO, String.format("%s requested to create a new account but details were rejected", createNewAccountReqDto.getUsername()));
            return "create-account";
        }

        loggger.log(Level.INFO, String.format("%s requested to created a new account", createNewAccountReqDto.getUsername()));
        userService.createNewUser(createNewAccountReqDto);
        farmService.createFarm(createNewAccountReqDto.getUsername());
        return "redirect:/login";
    }
}
