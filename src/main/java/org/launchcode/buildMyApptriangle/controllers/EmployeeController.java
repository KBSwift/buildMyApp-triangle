package org.launchcode.buildMyApptriangle.controllers;

import jakarta.validation.Valid;
import org.launchcode.buildMyApptriangle.models.Employee;
import org.launchcode.buildMyApptriangle.models.data.EmployeeRepository;
import org.launchcode.buildMyApptriangle.models.data.RoleRepository;
import org.launchcode.buildMyApptriangle.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees/index";
    }

    @GetMapping("add")
    public String displayAddEmployeeForm(Model model) {
        model.addAttribute(new Employee());
        return "employees/add";
    }

    @PostMapping(
            value = "add",
            // In order to export to database when encrypted, the data has to be changed to a specific type.
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public String processAddEmployeeForm(@ModelAttribute @Valid Employee newEmployee,
                                         Errors errors, Model model) {
        newEmployee.setEmployeeRoles(Arrays.asList(roleRepository.findByName("ROLE_EMPLOYEE")));
        newEmployee.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
        if (errors.hasErrors()) {
            return "register";
        }

        try {
            userDetailsService.loadUserByUsername(newEmployee.getUsername());
        }   catch (Exception UsernameNotFoundException) {
            userDetailsService.createEmployee(newEmployee);
            return "redirect:";
        }
        return "register";
    }

    @GetMapping("delete")
    public String displayDeleteEmployeeForm(Model model) {
        return "employees/delete";
    }

    @PostMapping("delete")
    public String processDeleteEmployeeForm(@RequestParam("username") String username) {
        Optional optionalEmployee = employeeRepository.findEmployeeByUsername(username);
        if (optionalEmployee.isPresent()) {
            Employee employee = (Employee) optionalEmployee.get();
            employeeRepository.deleteById(employee.getId());
            return "redirect:";
        }
        else {
            return "employees/delete";
        }
    }

//    @GetMapping("view/{employeeId}")
}