package org.launchcode.buildMyApptriangle.controllers;

import org.launchcode.buildMyApptriangle.models.User;
import org.launchcode.buildMyApptriangle.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping(
            value = "/register",
            // In order to export to database when encrypted, the data has to be changed to a specific type.
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public String addUser(@RequestParam Map<String, String> body) {
        User user = new User(); user.setUsername(body.get("username"));
        user.setPassword(passwordEncoder.encode(body.get("password")));
        try {
            userDetailsService.loadUserByUsername(user.getUsername());
        }   catch (Exception UsernameNotFoundException) {
            userDetailsService.createUser(user);
            return "redirect:/login";
        }
        return "register";
    }
}
