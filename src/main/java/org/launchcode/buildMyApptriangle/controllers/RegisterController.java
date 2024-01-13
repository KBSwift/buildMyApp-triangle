package org.launchcode.buildMyApptriangle.controllers;

import jakarta.validation.Valid;
import org.launchcode.buildMyApptriangle.models.User;
import org.launchcode.buildMyApptriangle.models.data.RoleRepository;
import org.launchcode.buildMyApptriangle.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("register")
    public String registerCustomer(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @PostMapping(
            value = "register",
            // In order to export to database when encrypted, the data has to be changed to a specific type.
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public String addCustomer(@ModelAttribute @Valid User newUser,
                          Errors errors, Model model) {
        newUser.setRoles(Arrays.asList(roleRepository.findByName("ROLE_CUSTOMER")));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if (errors.hasErrors()) {
            return "register";
        }

        try {
            userDetailsService.loadUserByUsername(newUser.getUsername());
        }   catch (Exception UsernameNotFoundException) {
            userDetailsService.createUser(newUser);
            return "redirect:/login";
        }
        return "register";
    }

    @GetMapping("register/employee")
    public String registerEmployee(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @PostMapping(
            value = "register/employee",
            // In order to export to database when encrypted, the data has to be changed to a specific type.
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public String addEmployee(@ModelAttribute @Valid User newUser,
                          Errors errors, Model model) {
        newUser.setRoles(Arrays.asList(roleRepository.findByName("ROLE_EMPLOYEE")));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if (errors.hasErrors()) {
            return "register";
        }

        try {
            userDetailsService.loadUserByUsername(newUser.getUsername());
        }   catch (Exception UsernameNotFoundException) {
            userDetailsService.createUser(newUser);
            return "redirect:/login";
        }
        return "register";
    }

// Old registration method that didn't include id.
//    @GetMapping("register")
//    public String register() {
//        return "register";
//    }
//
//    @PostMapping(
//            value = "/register",
//            // In order to export to database when encrypted, the data has to be changed to a specific type.
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
//            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
//    )
//    public String addUser(@RequestParam Map<String, String> body) {
//        User user = new User();
//        user.setUsername(body.get("username"));
//        user.setPassword(passwordEncoder.encode(body.get("password")));
//        user.setFirstName(body.get("firstName"));
//        user.setLastName(body.get("lastName"));
//        try {
//            userDetailsService.loadUserByUsername(user.getUsername());
//        }   catch (Exception UsernameNotFoundException) {
//            userDetailsService.createUser(user);
//            return "redirect:/login";
//        }
//        return "register";
//    }
}
