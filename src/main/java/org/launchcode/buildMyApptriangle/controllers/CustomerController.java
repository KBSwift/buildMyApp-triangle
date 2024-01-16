package org.launchcode.buildMyApptriangle.controllers;

import jakarta.validation.Valid;
import org.launchcode.buildMyApptriangle.models.Customer;
import org.launchcode.buildMyApptriangle.models.Customer;
import org.launchcode.buildMyApptriangle.models.Employee;
import org.launchcode.buildMyApptriangle.models.data.CustomerRepository;
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
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers/index";
    }

    @GetMapping("add")
    public String displayAddCustomerForm(Model model) {
        model.addAttribute(new Customer());
        return "customers/add";
    }

    @PostMapping(
            value = "add",
            // In order to export to database when encrypted, the data has to be changed to a specific type.
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public String processAddCustomerForm(@ModelAttribute @Valid Customer newCustomer,
                                         Errors errors, Model model) {
        newCustomer.setCustomerRoles(Arrays.asList(roleRepository.findByName("ROLE_CUSTOMER")));
        newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
        if (errors.hasErrors()) {
            return "customers/add";
        }

        try {
            userDetailsService.loadUserByUsername(newCustomer.getUsername());
        }   catch (Exception UsernameNotFoundException) {
            userDetailsService.createCustomer(newCustomer);
            return "redirect:/login";
        }
        return "register";
    }

    @GetMapping("delete")
    public String displayDeleteCustomerForm(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers/delete";
    }

    @PostMapping("delete")
    public String processDeleteCustomerForm(@ModelAttribute @Valid Customer deleteCustomer) {
        customerRepository.deleteById(deleteCustomer.getId());
        return "redirect:";
    }

    @GetMapping("view/{id}")
    public String displayViewCustomer(Model model, @PathVariable Long id) {
        Optional optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = (Customer) optionalCustomer.get();
            model.addAttribute("customer", customer);
            return "customers/view";
        } else {
            return "redirect:/customers/";
        }
    }

    @GetMapping("view/{id}/update")
    public String displayUpdateCustomer(Model model, @PathVariable Long id) {
        Optional optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = (Customer) optionalCustomer.get();
            model.addAttribute("customer", customer);
            return "customers/update";
        } else {
            return "redirect:/customers/";
        }
    }

    @PostMapping(
            value = "view/{id}/update",
            // In order to export to database when encrypted, the data has to be changed to a specific type.
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public String processUpdateCustomer(Model model, @PathVariable Long id, @ModelAttribute @Valid Customer customer,
                                        Errors errors) {
        if (errors.hasErrors()) {
            return "view/"+ id + "/update";
        }
        else {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);
        }
        return "redirect:/customers/view/" + id;
    }
}