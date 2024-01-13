package org.launchcode.buildMyApptriangle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customers")
public class CustomerController {

    @GetMapping("/")
    public String index() {
        return "customers/index";
    }

    @GetMapping("add")
    public String displayAddCustomerForm() {
        return "customers/add";
    }

    @GetMapping("delete")
    public String displayDeleteCustomerForm() {
        return "customers/delete";
    }

//    @GetMapping("view/{customerId}")
}