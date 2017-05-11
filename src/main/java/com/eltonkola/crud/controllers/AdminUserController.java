package com.eltonkola.crud.controllers;

import com.eltonkola.crud.domain.User;
import com.eltonkola.crud.form.UserCreateForm;
import com.eltonkola.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminUserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/admin/users/new", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("newUser", new User());

        return "admin/users_new";
    }

    @RequestMapping(value = "/admin/users/new", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserCreateForm userForm, BindingResult bindingResult, Model model) {
//        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/users_new";
        }

        userService.create(userForm);

        //securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/admin/users";
    }

    /*
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    */

}