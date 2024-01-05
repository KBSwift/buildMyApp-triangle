package org.launchcode.buildMyApptriangle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Customers")
public class CustomerController {

    @GetMapping("/")
    public String index() {
        return "customers/index";
    }

    @GetMapping("Add")
    public String displayAddCustomerForm() {
        return "customers/add";
    }

    @GetMapping("Delete")
    public String displayDeleteCustomerForm() {
        return "customers/delete";
    }

//    @GetMapping("view/{customerId}")
}