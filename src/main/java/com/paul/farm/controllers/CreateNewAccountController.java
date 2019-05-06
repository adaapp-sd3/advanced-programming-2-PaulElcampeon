package com.paul.farm.controllers;

import com.paul.farm.dtos.CreateNewAccountReqDto;
import com.paul.farm.services.interfaces.FarmService;
import com.paul.farm.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CreateNewAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private FarmService farmService;

    @RequestMapping(value = "/create-account", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute @Valid CreateNewAccountReqDto createNewAccountReqDto, BindingResult result) {

        if (result.hasErrors()) {
            return "/create-account";
        }

        userService.createNewUser(createNewAccountReqDto);
        farmService.createFarm(createNewAccountReqDto.getUsername());
        return "redirect:/login";
    }
}
