package com.paul.farm.controllers;

import com.paul.farm.dtos.CreateNewAccountReqDto;
import com.paul.farm.dtos.LoginReqDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ViewController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("loginReqDto", new LoginReqDto());
        return "login.html";
    }

    @RequestMapping(value = "/create-account", method = RequestMethod.GET)
    public String createAccountPage(Model model) {
        model.addAttribute("createNewAccountReqDto", new CreateNewAccountReqDto());
        return "create-account.html";
    }

    @RequestMapping(value = "/farm", method = RequestMethod.GET)
    public String homePage(Model model, Principal principal) {
        model.addAttribute("farmName", principal.getName());
        return "farm.html";
    }

    private String getUsernameFromPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
