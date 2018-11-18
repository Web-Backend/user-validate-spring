package com.longhoang.controllers;

import com.longhoang.models.User;
import com.longhoang.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/create-user")
    public ModelAndView showCreateForm() {
        return new ModelAndView("create", "user", new User());
    }

    @PostMapping("/create-user")
    public ModelAndView createUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");
        }
        else {
            userService.save(user);

            ModelAndView modelAndView = new ModelAndView("result", "message", "Created successfully");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
    }
}
