package org.launchcode.buildMyApptriangle.controllers;

import jakarta.validation.Valid;
import org.launchcode.buildMyApptriangle.models.User;
import org.launchcode.buildMyApptriangle.models.data.UserRepository;
import org.launchcode.buildMyApptriangle.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {
//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @GetMapping("register")
//    public String register(Model model) {
//        return "register";
//    }
//
//    @PostMapping("register")
//    public void addUser(@RequestParam String username, @RequestParam String password, Model model) {
//        User user = new User(username, password);
//        model.addAttribute("user", user);
//        userRepository.save(user);
//    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public void addUser(@RequestParam Map<String, String> body) {
        User user = new User(); user.setUsername(body.get("username"));
        user.setPassword(passwordEncoder.encode(body.get("password")));
        userDetailsService.createUser(user);
    }
}
